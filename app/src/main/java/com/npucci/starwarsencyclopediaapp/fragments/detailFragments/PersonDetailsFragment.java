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
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;

public class PersonDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.person_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        PersonDetailsFragmentArgs args = PersonDetailsFragmentArgs.fromBundle(getArguments());
        Person person = args.getPerson();

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(person.getName());

        TextView birthYearTextView = view.findViewById(R.id.birth_year_text_view);
        birthYearTextView.setText("Birth Year: " + person.getBirthYear());

        TextView eyeColorTextView = view.findViewById(R.id.eye_color_text_view);
        eyeColorTextView.setText("Eye Color: " + person.getEyeColor());

        TextView genderTextView = view.findViewById(R.id.gender_text_view);
        genderTextView.setText("Gender: " + person.getGender());

        TextView hairColorTextView = view.findViewById(R.id.hair_color_text_view);
        hairColorTextView.setText("Hair Color: " + person.getHairColor());

        TextView heightTextView = view.findViewById(R.id.height_text_view);
        heightTextView.setText("Height: " + person.getHeight());

        TextView massTextView = view.findViewById(R.id.mass_text_view);
        massTextView.setText("Mass: " + person.getMass());

        TextView skinColorTextView = view.findViewById(R.id.skin_color_text_view);
        skinColorTextView.setText("Skin Color: " + person.getSkinColor());

        TextView createdDateTextView = view.findViewById(R.id.created_date_text_view);
        createdDateTextView.setText("Created Date: " + person.getCreated());

        TextView editedDateTextView = view.findViewById(R.id.edited_date_text_view);
        editedDateTextView.setText("Edited Date: " + person.getEdited());

        Button filmsButton = view.findViewById(R.id.films_button);
        if(person.getFilmIDs().length == 0) {
            filmsButton.setEnabled(false);
        }
        else {
            filmsButton.setEnabled(true);
            filmsButton.setOnClickListener(v -> {
                PersonDetailsFragmentDirections.ActionPersonDetailsFragmentToFilmsListFragment action =
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToFilmsListFragment(person.getFilmIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button homeworldButton = view.findViewById(R.id.homeworld_button);
        if(person.getHomeworldIDs().length == 0) {
            homeworldButton.setEnabled(false);
        }
        else {
            homeworldButton.setEnabled(true);
            homeworldButton.setOnClickListener(v -> {
                PersonDetailsFragmentDirections.ActionPersonDetailsFragmentToPlanetsListFragment action =
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToPlanetsListFragment(person.getHomeworldIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button speciesButton = view.findViewById(R.id.species_button);
        if(person.getSpeciesIDs().length == 0) {
            speciesButton.setEnabled(false);
        }
        else {
            speciesButton.setEnabled(true);
            speciesButton.setOnClickListener(v -> {
                PersonDetailsFragmentDirections.ActionPersonDetailsFragmentToSpeciesListFragment action =
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToSpeciesListFragment(person.getSpeciesIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button starshipsButton = view.findViewById(R.id.starships_button);
        if(person.getStarshipIDs().length == 0) {
            starshipsButton.setEnabled(false);
        }
        else {
            starshipsButton.setEnabled(true);
            starshipsButton.setOnClickListener(v -> {
                PersonDetailsFragmentDirections.ActionPersonDetailsFragmentToStarshipsListFragment action =
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToStarshipsListFragment(person.getStarshipIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button vehiclesButton = view.findViewById(R.id.vehicles_button);
        if(person.getVehicleIDs().length == 0) {
            vehiclesButton.setEnabled(false);
        }
        else {
            vehiclesButton.setEnabled(true);
            vehiclesButton.setOnClickListener(v -> {
                PersonDetailsFragmentDirections.ActionPersonDetailsFragmentToVehiclesListFragment action =
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToVehiclesListFragment(person.getVehicleIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

    }
}