package work.derrick.diabetescarbtracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private double totalCarbs = 0;
    private TextView totalCarbsTextView;
    ArrayList<Food> foodList = new ArrayList<Food>();
    private ArrayList<String> foodNameList = new ArrayList<String>();
    private ArrayList<Food> foodChosenList = new ArrayList<Food>();
    private ArrayList<String> foodChosenNameList = new ArrayList<String>();
    private TextView spinnerChoiceTextView;
    private Food foodSelected;
    private Button addFoodButton;
    private TextView grams;
    private RecyclerView rv;
    private List<Food> mFoods;
    private ListView foodChosenListView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodChosenListView = (ListView) findViewById(R.id.foodListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, foodChosenNameList);

        foodChosenListView.setAdapter(adapter);

        grams = (TextView) findViewById(R.id.addGrams);
        spinnerChoiceTextView = (TextView) findViewById(R.id.spinner_choice);

        totalCarbsTextView = (TextView) findViewById(R.id.total_carbs_number);
        totalCarbsTextView.setText(Double.toString(totalCarbs));

        foodList.add(new Food("100% NATURAL CEREAL", 18, 28.35));
        foodList.add(new Food("1000 ISLAND, SALAD DRSNG,LOCAL", 2, 15));
        foodList.add(new Food("1000 ISLAND, SALAD DRSNG,REGLR", 2, 16));
        foodList.add(new Food("40% BRAN FLAKES, KELLOGG'S", 22, 28.35));
        foodList.add(new Food("40% BRAN FLAKES, POST", 22, 28.35));

        for(int i = 0; i < foodList.size(); i++){
            foodNameList.add(foodList.get(i).getDescription());
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, foodNameList);
        spinner.setAdapter(spinnerAdapter);

        addFoodButton = (Button) findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodChosenList.add(foodSelected);
                foodChosenNameList.add(foodSelected.getDescription());
                totalCarbs = totalCarbs + (foodSelected.getCarbPerWeight() * Double.parseDouble(grams.getText().toString()));
                totalCarbsTextView.setText(Double.toString(totalCarbs));
                Toast.makeText(MealActivity.this, "New Food added!", Toast.LENGTH_SHORT).show();
                grams.setText("");

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_calories);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalCarbs += 10;
                totalCarbsTextView.setText(Double.toString(totalCarbs));


            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();

        for (Food food : foodList){
            if(food.getDescription().equals(item)){
                foodSelected = food;
                Toast.makeText(MealActivity.this, "new food selected!!!!", Toast.LENGTH_SHORT).show();
            }
        }
        spinnerChoiceTextView.setText(foodSelected.getDescription());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initializeData(){
        mFoods = new ArrayList<>();
        mFoods.add(new Food("100% NATURAL CEREAL", 18, 28.35));
        mFoods.add(new Food("1000 ISLAND, SALAD DRSNG,LOCAL", 2, 15));
        mFoods.add(new Food("1000 ISLAND, SALAD DRSNG,REGLR", 2, 16));
        mFoods.add(new Food("40% BRAN FLAKES, KELLOGG'S", 22, 28.35));
        mFoods.add(new Food("40% BRAN FLAKES, POST", 22, 28.35));


    }


}
