package com.npucci.starwarsencyclopediaapp.fragments.detailFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.pojos.Film;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataViewModels.FilmViewModel;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class FilmDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.film_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        FilmDetailsFragmentArgs filmFragmentArgs = FilmDetailsFragmentArgs.fromBundle(getArguments());
        Film film = filmFragmentArgs.getFilm();

        TextView titleTextView = view.findViewById(R.id.title_text_view);
        titleTextView.setText("Star Wars: " + film.getTitle());

        TextView episodeNumberTextView = view.findViewById(R.id.episode_number_text_view);
        episodeNumberTextView.setText("(Episode " + film.getEpisodeID() + ")");

        TextView directorTextView = view.findViewById(R.id.director_text_view);
        directorTextView.setText("Director: " + film.getDirector());

        TextView producerTextView = view.findViewById(R.id.producer_text_view);
        producerTextView.setText("Producer: " + film.getProducer());

        TextView realeaseDateTextView = view.findViewById(R.id.release_date_text_view);
        realeaseDateTextView.setText("Released: " + film.getReleaseDate());

        TextView createdDateTextView = view.findViewById(R.id.created_date_text_view);
        createdDateTextView.setText(OffsetDateTime.parse(film.getCreated()).format(DateTimeFormatter.RFC_1123_DATE_TIME));

        TextView editedDateTextView = view.findViewById(R.id.edited_date_text_view);
        editedDateTextView.setText(OffsetDateTime.parse(film.getEdited()).format(DateTimeFormatter.RFC_1123_DATE_TIME));

        TextView descriptionTextView = view.findViewById(R.id.description_text_view);
        descriptionTextView.setText(film.getOpeningCrawl());

        Button charactersButton = view.findViewById(R.id.characters_button);
        if(film.getCharacterIDs().length == 0) {
            charactersButton.setEnabled(false);
        }
        else {
            charactersButton.setEnabled(true);
            charactersButton.setOnClickListener(v -> {
                FilmDetailsFragmentDirections.ActionFilmDetailsFragmentToCharactersListFragment action = FilmDetailsFragmentDirections.actionFilmDetailsFragmentToCharactersListFragment(film.getCharacterIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }


        Button starshipsButton = view.findViewById(R.id.starships_button);
        if(film.getStarshipIDs().length == 0) {
            starshipsButton.setEnabled(false);
        }
        else {
            starshipsButton.setEnabled(true);
            starshipsButton.setOnClickListener(v -> {
                FilmDetailsFragmentDirections.ActionFilmDetailsFragmentToStarshipsListFragment action = FilmDetailsFragmentDirections.actionFilmDetailsFragmentToStarshipsListFragment(film.getStarshipIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button vehiclesButton = view.findViewById(R.id.vehicles_button);
        if(film.getVehicleIDs().length == 0) {
            vehiclesButton.setEnabled(false);
        }
        else {
            vehiclesButton.setEnabled(true);
            vehiclesButton.setOnClickListener(v -> {
                FilmDetailsFragmentDirections.ActionFilmDetailsFragmentToVehiclesListFragment action = FilmDetailsFragmentDirections.actionFilmDetailsFragmentToVehiclesListFragment(film.getVehicleIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button planetsButton = view.findViewById(R.id.planets_button);
        if(film.getPlanetIDs().length == 0) {
            planetsButton.setEnabled(false);
        }
        else {
            planetsButton.setEnabled(true);
            planetsButton.setOnClickListener(v -> {
                FilmDetailsFragmentDirections.ActionFilmDetailsFragmentToPlanetsListFragment action = FilmDetailsFragmentDirections.actionFilmDetailsFragmentToPlanetsListFragment(film.getPlanetIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button speciesButton = view.findViewById(R.id.species_button);
        if(film.getSpeciesIDs().length == 0) {
            speciesButton.setEnabled(false);
        }
        else {
            speciesButton.setEnabled(true);
            speciesButton.setOnClickListener(v -> {
                FilmDetailsFragmentDirections.ActionFilmDetailsFragmentToSpeciesListFragment action = FilmDetailsFragmentDirections.actionFilmDetailsFragmentToSpeciesListFragment(film.getSpeciesIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }
    }
}