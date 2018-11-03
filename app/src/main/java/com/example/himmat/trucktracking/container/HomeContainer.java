package com.example.himmat.trucktracking.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.himmat.trucktracking.R;
import com.example.himmat.trucktracking.fragment.HomeFragment;

public class HomeContainer extends BaseContainer {
    private HomeFragment home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (home == null)
            home = new HomeFragment();
        replaceFragment(home);
    }
}
