package com.hfad.starbuzz;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        // Get drink from intent
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);
        Drink drink = Drink.drinks[drinkNo];

        // Drink image
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());

        // Drink name
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(drink.getName());

        // Drink description
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(drink.getDescription());

    }
}
