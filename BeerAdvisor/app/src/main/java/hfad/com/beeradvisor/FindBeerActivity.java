package hfad.com.beeradvisor;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;

public class FindBeerActivity extends Activity {

    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    // Called when the user clicks the button
    public void onClickFindBeer(View view){

        // Get Spinner, brands by ID
        Spinner color = (Spinner) findViewById(R.id.color);
        TextView brands = (TextView) findViewById(R.id.brands);

        // Get recommandation from the BeerExpert class
        String beerType = String.valueOf(color.getSelectedItem());
        List<String> brandList = expert.getBrands(beerType);
        StringBuilder brandsFormatted = new StringBuilder();

        for(String brand : brandList){
            brandsFormatted.append(brand).append('\n');
        }

        // Display beer in text view
        brands.setText(brandsFormatted);

    }
}
