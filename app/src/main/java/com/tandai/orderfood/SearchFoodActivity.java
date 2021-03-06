package com.tandai.orderfood;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.tandai.orderfood.Adapter.FoodAdapter1;
import com.tandai.orderfood.Model.Food;
import com.tandai.orderfood.Model.MonAn;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * CreatedBy: PQ Huy
 */
public class SearchFoodActivity extends AppCompatActivity {
    MaterialSearchBar materialSearchBar;
    ListView lvFood;
    ArrayList<Food> arrFood;
    ArrayList<Food> arrFoodSearch;
    FoodAdapter1 adapter = null;
    FoodAdapter1 adapterSearch = null;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getUid();
    DatabaseReference mDatabase;
    ArrayList<String> suggestList = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Note  add this code before setcontentView
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Rubik.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.layout_search_food);
        //T??m ki???m
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
        materialSearchBar.setHint("Nh???p t??n m??n ??n");
        // end tiem kiem
        lvFood  =   (ListView) findViewById(R.id.listview_food);
        arrFood = new ArrayList<>();
        adapter = new FoodAdapter1(this, R.layout.item_food, arrFood);
        arrFoodSearch = new ArrayList<>();
        adapterSearch = new FoodAdapter1(this, R.layout.item_food, arrFoodSearch);

        loadDataAllFood();
        loadSuggest();
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Khi ng?????i d??ng nh???p v??o SearchBar danh s??ch g???i ?? s??? thay ?????i theo.
                ArrayList<String> suggest = new ArrayList<String>();
                for(String search:suggestList){ // V??ng l???p suggetList
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //Khi Search Bar b??? ????ng
                //Kh??i ph???c adapter ban ?????u
                if(!enabled) {
                    //lvFood.setAdapter(adapter);
                    lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            //position l?? vi tri tren listview
                            Food foodSearch = arrFood.get(position);
                            Intent foodDetail = new Intent(SearchFoodActivity.this,FoodDetailActivity.class);
                            //g???i FoodId (ten c???a Food) v?? id qu??n ?????n activity FoodDetail
                            foodDetail.putExtra("FoodId",foodSearch.getTenMon());
                            foodDetail.putExtra("RestaurentID",foodSearch.getIDQuan());
                            // m??? activity  foodDetail
                            startActivity(foodDetail);
                        }
                    });
                    lvFood.setAdapter(adapter);

                    arrFoodSearch.clear();
                }
            }

            @Override
            public void onSearchConfirmed(final CharSequence text) {
                //Khi Search ho??n t???t
                //hi???n th??? k???t qu??? c???a searchAdapter
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


    }

    private void startSearch(final CharSequence text) {

        mDatabase = FirebaseDatabase.getInstance().getReference().child("QuanAn");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    for(DataSnapshot ds1: ds.getChildren()){
                        MonAn mon = ds1.getValue(MonAn.class);
                        if(mon.getTenMon().equals(text.toString())){
                            arrFoodSearch.add(new Food(mon.getTenMon(),mon.getTenQuan(),mon.getLinkAnh(),mon.getIdQuan(),mon.getGiaMon(),mon.getTinhTrang()));
                            adapterSearch.notifyDataSetChanged();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addListenerForSingleValueEvent(eventListener);


        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position l?? vi tri tren listview
                Food foodSearch = arrFoodSearch.get(position);
                Intent foodDetail = new Intent(SearchFoodActivity.this,FoodDetailActivity.class);
                //g???i FoodId (ten c???a Food) v?? id qu??n ?????n activity FoodDetail
                foodDetail.putExtra("FoodId",foodSearch.getTenMon());
                foodDetail.putExtra("RestaurentID",foodSearch.getIDQuan());
                // m??? activity  foodDetail
                startActivity(foodDetail);
            }
        });
        lvFood.setAdapter(adapterSearch);


    }

    private  void loadDataAllFood(){

        mDatabase = FirebaseDatabase.getInstance().getReference().child("QuanAn");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    for(DataSnapshot ds1: ds.getChildren()){
                        MonAn mon = ds1.getValue(MonAn.class);
                        arrFood.add(new Food(mon.getTenMon(),mon.getTenQuan(),mon.getLinkAnh(),mon.getIdQuan(),mon.getGiaMon(),mon.getTinhTrang()));
                        adapter.notifyDataSetChanged();
                    }
                }


                lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        //position l?? vi tri tren listview
                        Food food = arrFood.get(position);
                        Intent foodDetail = new Intent(SearchFoodActivity.this,FoodDetailActivity.class);
                        //g???i FoodId (ten c???a Food) v?? id qu??n ?????n activity FoodDetail
                        foodDetail.putExtra("FoodId",food.getTenMon());
                        foodDetail.putExtra("RestaurentID",food.getIDQuan());
                        // m??? activity  foodDetail
                        startActivity(foodDetail);
                    }
                });
                lvFood.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addListenerForSingleValueEvent(eventListener);




    }

    private void loadSuggest() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("QuanAn");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren())
                {
                    for(DataSnapshot ds1: ds.getChildren()) {
                        Food food = ds1.getValue(Food.class);
                        suggestList.add(food.getTenMon()); //Th??m t??n m??n ??n v??o danh s??ch g???i ??
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }






}