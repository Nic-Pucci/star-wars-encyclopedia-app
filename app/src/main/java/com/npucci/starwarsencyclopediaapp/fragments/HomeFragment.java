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

        Button browseCharactersButton = view.findViewById(R.id.browse_characters_button);
        browseCharactersButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToCharactersFragment action =
                    HomeFragmentDirections.actionMainFragmentToCharactersFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });

        Button browseFilmsButton = view.findViewById(R.id.browse_films_button);
        browseFilmsButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToFilmsFragment action =
                    HomeFragmentDirections.actionMainFragmentToFilmsFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });

        Button browsePlanetsButton = view.findViewById(R.id.browse_planets_button);
        browsePlanetsButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToPlanetsFragment action =
                    HomeFragmentDirections.actionMainFragmentToPlanetsFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });


        Button browseSpeciesButton = view.findViewById(R.id.browse_species_button);
        browseSpeciesButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToSpeciesFragment action =
                    HomeFragmentDirections.actionMainFragmentToSpeciesFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });


        Button browseStarshipsButton = view.findViewById(R.id.browse_starships_button);
        browseStarshipsButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToStarshipsFragment action =
                    HomeFragmentDirections.actionMainFragmentToStarshipsFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });


        Button browseVehiclesButton = view.findViewById(R.id.browse_vehicles_button);
        browseVehiclesButton.setOnClickListener(v -> {
            HomeFragmentDirections.ActionMainFragmentToVehiclesFragment action =
                    HomeFragmentDirections.actionMainFragmentToVehiclesFragment(new int[0]);
            NavControllerUtil.navigate(navController, action);
        });
    }
}