package com.example.ramdanariadi.mobileproject.Activity;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ramdanariadi.mobileproject.Fragment.HomeFragment;
import com.example.ramdanariadi.mobileproject.Fragment.OneFragment;
import com.example.ramdanariadi.mobileproject.Fragment.SearchFragment;
import com.example.ramdanariadi.mobileproject.Fragment.TwoFragment;
import com.example.ramdanariadi.mobileproject.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    EditText toolbarEditText;
    MenuItem toolbarMenuItemSearch;
    Boolean isSearchBarOpened = false;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        configureViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //hideSearchFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbarMenuItemSearch = menu.findItem(R.id.menu_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting: return true;
            case R.id.menu_search: handleMenuSearch(); return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void handleMenuSearch(){
        ActionBar actionBar = getSupportActionBar();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(isSearchBarOpened){
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);

            inputMethodManager.hideSoftInputFromWindow(toolbarEditText.getWindowToken(),0);

            toolbarMenuItemSearch.setIcon(R.drawable.ic_action_search);
            isSearchBarOpened = false;

            hideSearchFragment();
        }else {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.search_layout);

            toolbarEditText = (EditText) findViewById(R.id.edtSearch);

//            toolbarEditText.getBackground().clearColorFilter();

            toolbarEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_SEARCH){
//
                        return true;
                    }
                    return false;
                }
            });
            showSearchFragment();

            toolbarEditText.requestFocus();

            inputMethodManager.showSoftInput(toolbarEditText,InputMethodManager.SHOW_IMPLICIT);

            toolbarMenuItemSearch.setIcon(R.drawable.ic_action_cross);
            isSearchBarOpened = true;
        }
    }

    void showSearchFragment(){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        viewPager.setVisibility(View.INVISIBLE);
        AppBarLayout.LayoutParams tabLayoutParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        tabLayoutParams.height = 0;
        tabLayout.setLayoutParams(tabLayoutParams);
        searchFragment = new SearchFragment();

        fragmentTransaction.replace(R.id.activity_main,searchFragment);
//        fragmentTransaction.addToBackStack("searchFragment");
        fragmentTransaction.commit();
    }
    SearchFragment searchFragment;
    void hideSearchFragment(){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        viewPager.setVisibility(View.VISIBLE);
        AppBarLayout.LayoutParams tabLayoutParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        tabLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        tabLayout.setLayoutParams(tabLayoutParams);
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.remove(searchFragment);
        fragmentTransaction.commit();
        Log.i("fragment info : ","framgent removed");
    }

    void configureViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new OneFragment(),"Home");
        viewPagerAdapter.addFragment(new TwoFragment(),"New");
        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        final List<Fragment> fragments = new ArrayList<>();
        final List<String> fragmentTittle = new ArrayList<>();

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTittle.add(title);
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTittle.get(position);
        }
    }
}
