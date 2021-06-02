package com.npucci.starwarsencyclopediaapp.fragments.listFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.pojos.dataResults.DataResults;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.FilmsListViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class FilmsListFragment extends Fragment {
    private FilmsListViewModel filmsListViewModel;
    private ArrayAdapter arrayAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filmsListViewModel = new ViewModelProvider(this).get(FilmsListViewModel.class);

        setHasOptionsMenu(true);
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
        filmsListViewModel.updateDataResultsLive(filmIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Film> filmList = dataResults.getResults();

            List<String> filmDisplayStrings = filmList.stream().map(film -> film.getDisplayName()).collect(Collectors.toList());
            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, filmDisplayStrings);
            filmsListView.setAdapter(arrayAdapter);

            filmsListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Film selectedFilm = filmList.get(index);

                FilmsListFragmentDirections.ActionFilmsFragmentToFilmFragment action = FilmsListFragmentDirections.actionFilmsFragmentToFilmFragment(selectedFilm);
                NavControllerUtil.navigate(navController, action);
            });
        });

        filmsListViewModel.getErrorLive().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        final MenuItem searchViewMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        searchView.setQueryHint("Search ...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    DataResults<Film> dataResults = filmsListViewModel.getDataResultsLive().getValue();
                    List<String> unfilteredFilms = dataResults.getResults().stream()
                            .map(film -> film.getDisplayName())
                            .collect(Collectors.toList());
                    arrayAdapter.clear();
                    arrayAdapter.addAll(unfilteredFilms);
                    arrayAdapter.notifyDataSetChanged();
                    return true;
                }

                DataResults<Film> dataResults = filmsListViewModel.getDataResultsLive().getValue();
                List<String> filteredFilms = dataResults.getResults().stream()
                        .filter(film -> {
                            boolean queryInDisplayName = film.getDisplayName().toLowerCase().contains(newText);
                            boolean queryInDescription = film.getOpeningCrawl().toLowerCase().contains(newText);
                            return queryInDisplayName || queryInDescription;
                        })
                        .map(film -> film.getDisplayName())
                        .collect(Collectors.toList());

                arrayAdapter.clear();
                arrayAdapter.addAll(filteredFilms);
                arrayAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }
}