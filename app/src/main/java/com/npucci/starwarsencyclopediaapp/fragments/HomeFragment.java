package com.npucci.starwarsencyclopediaapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        Button browseFilmsButton = view.findViewById(R.id.browse_films_button);
        browseFilmsButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToFilmsFragment action = HomeFragmentDirections.actionMainFragmentToFilmsFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });
    }
}