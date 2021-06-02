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
import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels.FilmsListViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class FilmsListFragment extends Fragment {
    private FilmsListViewModel filmsListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filmsListViewModel = new ViewModelProvider(this).get(FilmsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.films_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        final ListView filmsListView = view.findViewById(R.id.films_list_view);

        FilmsListFragmentArgs args = FilmsListFragmentArgs.fromBundle(getArguments());
        int[] filmIDs = args.getFilmIDs();
        filmsListViewModel.getDataListLive(filmIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Film> filmList = dataResults.getResults();

            List<String> filmDisplayStrings = filmList.stream().map(film -> film.getDisplayName()).collect(Collectors.toList());
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, filmDisplayStrings);
            filmsListView.setAdapter(arrayAdapter);

            filmsListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Film selectedFilm = filmList.get(index);

                FilmsListFragmentDirections.ActionFilmsFragmentToFilmFragment action = FilmsListFragmentDirections.actionFilmsFragmentToFilmFragment(selectedFilm);
                NavControllerUtil.navigate(navController, action);
            });
        });
    }
}