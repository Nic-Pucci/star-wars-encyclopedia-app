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
import com.npucci.starwarsencyclopediaapp.pojos.Starship;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.StarshipListViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StarshipsListFragment extends Fragment {
    private StarshipListViewModel starshipListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        starshipListViewModel = new ViewModelProvider(this).get(StarshipListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.starships_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        ListView starshipListView = view.findViewById(R.id.starships_list_view);

        StarshipsListFragmentArgs args = StarshipsListFragmentArgs.fromBundle(getArguments());
        int[] starshipIDs = args.getStarshipIDs();
        starshipListViewModel.getDataListLive(starshipIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Starship> starshipList = dataResults.getResults();

            List<String> starshipDisplayStrings = starshipList.stream().map(starship -> starship.getName()).collect(Collectors.toList());
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, starshipDisplayStrings);
            starshipListView.setAdapter(arrayAdapter);

            starshipListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Starship selectedStarship = starshipList.get(index);

                StarshipsListFragmentDirections.ActionStarshipsListFragmentToStarshipDetailsFragment action =
                        StarshipsListFragmentDirections.actionStarshipsListFragmentToStarshipDetailsFragment(selectedStarship);
                NavControllerUtil.navigate(navController, action);
            });
        });
    }
}