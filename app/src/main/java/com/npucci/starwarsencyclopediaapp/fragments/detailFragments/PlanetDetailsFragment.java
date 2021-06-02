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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.pojos.Person;
import com.npucci.starwarsencyclopediaapp.pojos.Planet;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;

public class PlanetDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planet_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        PlanetDetailsFragmentArgs args = PlanetDetailsFragmentArgs.fromBundle(getArguments());
        Planet planet = args.getPlanet();

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(planet.getName());

        TextView diameterTextView = view.findViewById(R.id.diameter_text_view);
        diameterTextView.setText("Diameter: " + planet.getDiameter());

        TextView rotationalPeriodTextView = view.findViewById(R.id.rotational_period_text_view);
        rotationalPeriodTextView.setText("Rotational Period: " + planet.getRotationPeriod());

        TextView orbitalPeriodTextView = view.findViewById(R.id.orbital_period_text_view);
        orbitalPeriodTextView.setText("Orbital Period: " + planet.getOrbitalPeriod());

        TextView gravityTextView = view.findViewById(R.id.gravity_text_view);
        gravityTextView.setText("Gravity: " + planet.getGravity());

        TextView climateTextView = view.findViewById(R.id.climate_text_view);
        climateTextView.setText("Climate: " + planet.getClimate());

        TextView terrainTextView = view.findViewById(R.id.terrain_text_view);
        terrainTextView.setText("Terrain: " + planet.getTerrain());

        TextView surfaceWaterTextView = view.findViewById(R.id.surface_water_text_view);
        surfaceWaterTextView.setText("Surface Water: " + planet.getSurfaceWater());

        TextView createdDateTextView = view.findViewById(R.id.created_date_text_view);
        createdDateTextView.setText("Created Date: " + planet.getCreated());

        TextView editedDateTextView = view.findViewById(R.id.edited_date_text_view);
        editedDateTextView.setText("Edited Date: " + planet.getEdited());

        Button filmsButton = view.findViewById(R.id.films_button);
        if(planet.getFilmIDs().length == 0) {
            filmsButton.setEnabled(false);
        }
        else {
            filmsButton.setEnabled(true);
            filmsButton.setOnClickListener(v -> {
                PlanetDetailsFragmentDirections.ActionPlanetDetailsFragmentToFilmsListFragment action =
                        PlanetDetailsFragmentDirections.actionPlanetDetailsFragmentToFilmsListFragment(planet.getFilmIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button residentsButton = view.findViewById(R.id.residents_button);
        if(planet.getResidentIDs().length == 0) {
            residentsButton.setEnabled(false);
        }
        else {
            residentsButton.setEnabled(true);
            residentsButton.setOnClickListener(v -> {
                PlanetDetailsFragmentDirections.ActionPlanetDetailsFragmentToCharactersListFragment action =
                        PlanetDetailsFragmentDirections.actionPlanetDetailsFragmentToCharactersListFragment(planet.getResidentIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

    }
}