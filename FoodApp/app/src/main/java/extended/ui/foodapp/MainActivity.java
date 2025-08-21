package extended.ui.foodapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView btmView=findViewById(R.id.btm_nav);
        NavController controllerNav=Navigation.findNavController(this,R.id.main_fragment);
        NavigationUI.setupWithNavController(btmView,controllerNav);
    }
}