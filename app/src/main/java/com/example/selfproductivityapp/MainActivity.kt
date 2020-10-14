package com.example.selfproductivityapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.selfproductivityapp.entry.EntryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // delegates dropdown menu navigation to nav_host_fragment (points to nav_graph.xml)
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
        }
// TODO: Figure out what to do with the below

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onBackPressed() {
//        Log.i("backPressed", "back button listener activated~")
//        val found = this.supportFragmentManager.findFragmentById(R.id.main_container)
//        if (found is EntryFragment) {
//            Log.i("we out here", "out herrrrrreeeeeeee")
//            (found as OnBackPressedListener).onBackPressed()
//        } else {
//            super.onBackPressed()
//        }
//    }
}
