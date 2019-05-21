package manzi.ur.firebaseauthdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Arraylist extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist);

        listview= (ListView)findViewById(R.id.listview);

        ArrayList<String> words = new ArrayList<String>();
        words.add("MealType: Chicken");
        words.add("MealTime: 2Hours");
        words.add("Ingredients: Meat");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");
        words.add("MealTime: 2Hours");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,words);

        listview.setAdapter(itemsAdapter);
    }
}
