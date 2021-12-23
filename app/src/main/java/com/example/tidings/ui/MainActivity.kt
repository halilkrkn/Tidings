package com.example.tidings.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tidings.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tidings.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tidings)

        // Burada navigation kurulum ve confiqürasyon İşlemleri yapıldı.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()


        // Bottom Navigation Kurulumu Yapıldı.
        bottomNavigationView.setupWithNavController(navController)
        // Burada appBarda Geri Ok tuşu gözükmesi ve Hangi sayfada olunduğunu (Breaking, Saved gibi appBarda yazan) appBarda görmek için oluşturuldu.
        NavigationUI.setupActionBarWithNavController(this, navController)

        // TODO: 23.12.2021  Diğer Başka Bir Bottom-Navigation ve AppBarConfiguration Kurulumu
//        // Bottom Navigation Kurulumu Yapıldı.
//        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        navView.setupWithNavController(navController)

        // Burada appBarda Geri Ok tuşu gözükmesi ve Hangi sayfada olunduğunu (Breaking, Saved gibi appBarda yazan) appBarda görmek için oluşturuldu.
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}
