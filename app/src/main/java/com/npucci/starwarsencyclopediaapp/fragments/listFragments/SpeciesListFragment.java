package com.npucci.starwarsencyclopediaapp.fragments.listFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.pojos.Species;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.SpeciesListViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class SpeciesListFragment extends Fragment {
    private SpeciesListViewModel speciesListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        speciesListViewModel = new ViewModelProvider(this).get(SpeciesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.species_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        final ListView speciesListView = view.findViewById(R.id.species_list_view);

        SpeciesListFragmentArgs args = SpeciesListFragmentArgs.fromBundle(getArguments());
        int[] speciesIDs = args.getSpeciesIDs();
        speciesListViewModel.updateDataResultsLive(speciesIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Species> speciesList = dataResults.getResults();

            List<String> speciesDisplayStrings = speciesList.stream().map(species -> species.getName()).collect(Collectors.toList());
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, speciesDisplayStrings);
            speciesListView.setAdapter(arrayAdapter);

            speciesListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Species selectedSpecies = speciesList.get(index);

                SpeciesListFragmentDirections.ActionSpeciesListFragmentToSpeciesDetailsFragment action =
                        SpeciesListFragmentDirections.actionSpeciesListFragmentToSpeciesDetailsFragment(selectedSpecies);
                NavControllerUtil.navigate(navController, action);
            });
        });
    }
}