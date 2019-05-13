package ru.boytsunvitaliy.mobile1cweb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()/*, NavController.OnDestinationChangedListener*/ {

//    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//        val navController = Navigation.findNavController(this, R.id.navHostFragment)
//        navController.addOnDestinationChangedListener(this)
    }

//    override fun onDestinationChanged(
//        controller: NavController,
//        destination: NavDestination,
//        arguments: Bundle?
//    ) {
//        title = destination.label
//    }
}
