package com.entrpn.room.livedata.example.activities

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import com.entrpn.room.livedata.example.PeopleFragment
import com.entrpn.room.livedata.example.R
import com.entrpn.room.livedata.example.fragment.PeopleDetailFragment
import com.entrpn.room.livedata.example.viewmodel.PeopleListViewModel


class MainActivity : LifecycleActivity() {
    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    private var currentFragment: Int = 0


    /*//////////////////////////////////////////////////////////
    // LIFECYCLE OVERRIDES.
    *///////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restore(savedInstanceState)
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(CURRENT_FRAGMENT,currentFragment)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) finish()
    }


    /*//////////////////////////////////////////////////////////
    // PRIVATE METHODS
    *///////////////////////////////////////////////////////////
    private fun restore(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            loadFragment(PeopleFragment.getInstance(),PeopleFragment.TAG)
        } else {
            currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT)
            when (currentFragment) {
                FRAGMENT_PEOPLE -> {
                    val fragment = supportFragmentManager.findFragmentByTag(PeopleFragment.TAG)
                    if (fragment != null) {
                        loadFragment(fragment,PeopleFragment.TAG)
                    }
                }
                FRAGMENT_PEOPLE_DETAIL -> {
                    val fragment = supportFragmentManager.findFragmentByTag(PeopleDetailFragment.TAG)
                    if (fragment != null) {
                        loadFragment(fragment,PeopleDetailFragment.TAG)
                    }
                }
                else -> loadFragment(PeopleFragment.getInstance(),PeopleFragment.TAG)
            }
        }
        setObservers()
    }
    private fun loadFragment(@Nullable fragment: Fragment?, tag: String) {
        if (fragment == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,supportFragmentManager.findFragmentByTag(tag)).addToBackStack(tag).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(tag).commit()
        }
    }
    private fun setObservers() {
        val viewModel = ViewModelProviders.of(this@MainActivity).get(PeopleListViewModel::class.java)

        viewModel.getSelectedModel().observe(this@MainActivity, Observer {
            i -> i?.let {supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,PeopleDetailFragment.getInstance(i)).addToBackStack(PeopleDetailFragment.TAG).commit()}
        })
    }


    /*//////////////////////////////////////////////////////////
    // COMPANION OBJECT
    *///////////////////////////////////////////////////////////
    companion object {
        val FRAGMENT_PEOPLE = 0
        val FRAGMENT_PEOPLE_DETAIL = 1
        val CURRENT_FRAGMENT = "current_fragment"
    }

}
