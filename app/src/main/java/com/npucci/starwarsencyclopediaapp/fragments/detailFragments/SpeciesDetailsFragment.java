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
import com.npucci.starwarsencyclopediaapp.pojos.Species;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;

public class SpeciesDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.species_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        SpeciesDetailsFragmentArgs args = SpeciesDetailsFragmentArgs.fromBundle(getArguments());
        Species species = args.getSpecies();

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(species.getName());

        TextView classificationTextView = view.findViewById(R.id.classification_text_view);
        classificationTextView.setText("Classification: " + species.getClassification());

        TextView designationTextView = view.findViewById(R.id.designation_text_view);
        designationTextView.setText("Designation: " + species.getDesignation());

        TextView averageHeightTextView = view.findViewById(R.id.average_height_text_view);
        averageHeightTextView.setText("Average Height: " + species.getAverageHeight());

        TextView averageLifespanTextView = view.findViewById(R.id.average_lifespan_text_view);
        averageLifespanTextView.setText("Average Lifespan: " + species.getAverageLifespan());

        TextView eyeColorsTextView = view.findViewById(R.id.eye_colors_text_view);
        eyeColorsTextView.setText("Eye Colors: " + species.getEyeColors());

        TextView hairColorsTextView = view.findViewById(R.id.hair_colors_text_view);
        hairColorsTextView.setText("Hair Colors: " + species.getHairColors());

        TextView skinColorsTextView = view.findViewById(R.id.skin_colors_text_view);
        skinColorsTextView.setText("Skin Colors: " + species.getSkinColors());

        TextView languageTextView = view.findViewById(R.id.language_text_view);
        languageTextView.setText("Language: " + species.getLanguage());

        TextView createdDateTextView = view.findViewById(R.id.created_date_text_view);
        createdDateTextView.setText("Created Date: " + species.getCreated());

        TextView editedDateTextView = view.findViewById(R.id.edited_date_text_view);
        editedDateTextView.setText("Edited Date: " + species.getEdited());

        Button filmsButton = view.findViewById(R.id.films_button);
        if(species.getFilmIDs().length == 0) {
            filmsButton.setEnabled(false);
        }
        else {
            filmsButton.setEnabled(true);
            filmsButton.setOnClickListener(v -> {
                SpeciesDetailsFragmentDirections.ActionSpeciesDetailsFragmentToFilmsListFragment action =
                        SpeciesDetailsFragmentDirections.actionSpeciesDetailsFragmentToFilmsListFragment(species.getFilmIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button homeworldButton = view.findViewById(R.id.homeworld_button);
        if(species.getHomeworldIDs().length == 0) {
            homeworldButton.setEnabled(false);
        }
        else {
            homeworldButton.setEnabled(true);
            homeworldButton.setOnClickListener(v -> {
                SpeciesDetailsFragmentDirections.ActionSpeciesDetailsFragmentToPlanetsListFragment action =
                        SpeciesDetailsFragmentDirections.actionSpeciesDetailsFragmentToPlanetsListFragment(species.getHomeworldIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button peopleButton = view.findViewById(R.id.people_button);
        if(species.getPeopleIDs().length == 0) {
            peopleButton.setEnabled(false);
        }
        else {
            peopleButton.setEnabled(true);
            peopleButton.setOnClickListener(v -> {
                SpeciesDetailsFragmentDirections.ActionSpeciesDetailsFragmentToCharactersListFragment action =
                        SpeciesDetailsFragmentDirections.actionSpeciesDetailsFragmentToCharactersListFragment(species.getPeopleIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

    }
}