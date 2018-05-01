package au.com.inteliment.poc.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import au.com.inteliment.poc.R
import au.com.inteliment.poc.adapter.FragmentItemAdapter
import au.com.inteliment.poc.fragment.MainFragment
import au.com.inteliment.poc.fragment.Test1Fragment
import au.com.inteliment.poc.fragment.Test2Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainFragment.OnFragmentClick, Test2Fragment.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        initViewPager(viewPager)
        initTabLayout(viewPager)
    }

    override fun onResume() {
        super.onResume()
        checkPlayServices()
    }

    private fun initViewPager(viewPager: ViewPager?) {
        val adapter = FragmentItemAdapter(supportFragmentManager)
        adapter.addFragment(Test1Fragment.instance(), getString(R.string.fragment_test_1))
        adapter.addFragment(Test2Fragment.instance(), getString(R.string.fragment_test_2))
        viewPager?.adapter = adapter
    }

    private fun initTabLayout(viewPager: ViewPager?) {
        tabs.setupWithViewPager(viewPager)
    }

    //Called when fragment is clicked, displays fragment name passed while instantiating
    override fun onFragmentClick(fragmentName: String?) {
        Toast.makeText(this, fragmentName, Toast.LENGTH_SHORT).show()
    }

    override fun onClickNavigate(latitude: Double, longitude: Double) {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra(MapActivity.ARG_LATITUDE, latitude)
        intent.putExtra(MapActivity.ARG_LONGITUDE, longitude)
        startActivity(intent)
    }

    private fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show()
            } else {
                Toast.makeText(this, "This device doesn't has google play services.", Toast.LENGTH_SHORT).show()
            }
            return false
        }
        return true
    }

    companion object {
        private const val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
    }
}
