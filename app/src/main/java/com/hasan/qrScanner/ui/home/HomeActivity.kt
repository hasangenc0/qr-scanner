package com.hasan.qrScanner.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.hasan.qrScanner.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity(), ScanResultReceiver {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var drawerLayout: DrawerLayout? = null
    var toolbar: Toolbar? = null
    var navigation: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar_home)
        setSupportActionBar(toolbar) // Setting/replace toolbar_home as the ActionBar

        // Drawer Layout
        drawerLayout = findViewById(R.id.container)

        toolbar!!.setNavigationOnClickListener {
            if (drawerLayout !is DrawerLayout) return@setNavigationOnClickListener

            drawerLayout!!.openDrawer(Gravity.LEFT, true)
        }

        // Tabs
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // set viewPager
        val adapter = HomeAdapter(this, supportFragmentManager, 2)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        // Set Tab Layout
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        // Navigation
        navigation = findViewById<NavigationView>(R.id.navigation_sidebar)
        navigation!!.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout_link -> {
                    // Logout
                    moveTaskToBack(true)
                    exitProcess(-1)
                }
                R.id.homepage_link -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun updateUiWithScannedQrData(qr: String, tableId: Int, view: View?, context: Context?) {
        val table = view!!.findViewById<TableLayout>(tableId)

        // Table row
        val row = LayoutInflater.from(context).inflate(R.layout.table_row, table, false) as ViewGroup

        // Table Name col
        val qrCol = LayoutInflater.from(context).inflate(R.layout.text_view_long_words, row, false) as TextView
        qrCol.text = qr


        // Add rows to view
        table?.addView(row)
        row.addView(qrCol)


        // Show
        Snackbar.make(view, "QR Scanned", Snackbar.LENGTH_LONG).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sidebar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout_link -> {
                // Logout
                moveTaskToBack(true)
                exitProcess(-1)
            }
            R.id.homepage_link -> {
            }
        }

        return true
    }

}
