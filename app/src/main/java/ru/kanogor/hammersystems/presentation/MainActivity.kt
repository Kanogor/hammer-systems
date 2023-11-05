package ru.kanogor.hammersystems.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ru.kanogor.hammersystems.R
import ru.kanogor.hammersystems.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
                    as NavHostFragment
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu, R.id.profile, R.id.basket
            )
        )
        binding.navView.setupWithNavController(navController.navController)
        binding.navView.setOnItemReselectedListener { item ->
            navController.navController.popBackStack()
            navController.navController.navigate(item.itemId)
        }

        binding.arrowDown.setOnClickListener {
            navController.navController.popBackStack()
            navController.navController.navigate(R.id.city_list)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}