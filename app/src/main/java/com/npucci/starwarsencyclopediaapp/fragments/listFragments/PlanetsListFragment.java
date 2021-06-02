package com.npucci.starwarsencyclopediaapp.fragments.listFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.pojos.Planet;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.PlanetListViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class PlanetsListFragment extends Fragment {
    private PlanetListViewModel planetListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        planetListViewModel = new ViewModelProvider(this).get(PlanetListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planets_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        final ListView planetsListView = view.findViewById(R.id.planets_list_view);

        PlanetsListFragmentArgs args = PlanetsListFragmentArgs.fromBundle(getArguments());
        int[] planetIDs = args.getPlanetIDs();
        planetListViewModel.updateDataResultsLive(planetIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Planet> planetList = dataResults.getResults();

            List<String> planetsDisplayStrings = planetList.stream().map(planet -> planet.getName()).collect(Collectors.toList());
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, planetsDisplayStrings);
            planetsListView.setAdapter(arrayAdapter);

            planetsListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Planet selectedPlanet = planetList.get(index);

                PlanetsListFragmentDirections.ActionPlanetsListFragmentToPlanetDetailsFragment action =
                        PlanetsListFragmentDirections.actionPlanetsListFragmentToPlanetDetailsFragment(selectedPlanet);
                NavControllerUtil.navigate(navController, action);
            });
        });

        planetListViewModel.getErrorLive().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        });
    }
}