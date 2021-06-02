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
import com.npucci.starwarsencyclopediaapp.pojos.Starship;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;

public class StarshipDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.starship_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        StarshipDetailsFragmentArgs args = StarshipDetailsFragmentArgs.fromBundle(getArguments());
        Starship starship = args.getStarship();

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(starship.getName());

        TextView modelTextView = view.findViewById(R.id.model_text_view);
        modelTextView.setText("Model: " + starship.getModel());

        TextView starshipClassTextView = view.findViewById(R.id.starship_class_text_view);
        starshipClassTextView.setText("Starship Class: " + starship.getStarshipClass());

        TextView manufacturerTextView = view.findViewById(R.id.manufacturer_text_view);
        manufacturerTextView.setText("Manufacturer: " + starship.getManufacturer());

        TextView costInCreditsTextView = view.findViewById(R.id.cost_in_credits_text_view);
        costInCreditsTextView.setText("Cost in Credits: " + starship.getCostInCredits());

        TextView lengthTextView = view.findViewById(R.id.length_text_view);
        lengthTextView.setText("Length: " + starship.getLength());

        TextView passengersTextView = view.findViewById(R.id.passengers_text_view);
        passengersTextView.setText("Passengers: " + starship.getPassengers());

        TextView maxAtmospheringSpeedTextView = view.findViewById(R.id.max_atmosphering_speed_text_view);
        maxAtmospheringSpeedTextView.setText("Max Atmosphering Speed: " + starship.getMaxAtmospheringSpeed());

        TextView hyperdriveRatingTextView = view.findViewById(R.id.hyperdrive_rating_text_view);
        hyperdriveRatingTextView.setText("Hyperdrive Rating: " + starship.getHyperdriveRating());

        TextView mgltTextView = view.findViewById(R.id.mglt_text_view);
        mgltTextView.setText("MGLT: " + starship.getMglt());

        TextView cargoCapacityTextView = view.findViewById(R.id.cargo_capacity_text_view);
        cargoCapacityTextView.setText("Cargo Capacity: " + starship.getCargoCapacity());

        TextView consumablesTextView = view.findViewById(R.id.consumables_text_view);
        consumablesTextView.setText("Consumables: " + starship.getConsumables());

        TextView createdDateTextView = view.findViewById(R.id.created_date_text_view);
        createdDateTextView.setText("Created Date: " + starship.getCreated());

        TextView editedDateTextView = view.findViewById(R.id.edited_date_text_view);
        editedDateTextView.setText("Edited Date: " + starship.getEdited());

        Button filmsButton = view.findViewById(R.id.films_button);
        if(starship.getFilmIDs().length == 0) {
            filmsButton.setEnabled(false);
        }
        else {
            filmsButton.setEnabled(true);
            filmsButton.setOnClickListener(v -> {
                StarshipDetailsFragmentDirections.ActionStarshipDetailsFragmentToFilmsListFragment action =
                        StarshipDetailsFragmentDirections.actionStarshipDetailsFragmentToFilmsListFragment(starship.getFilmIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button homeworldButton = view.findViewById(R.id.pilots_button);
        if(starship.getPilotIDs().length == 0) {
            homeworldButton.setEnabled(false);
        }
        else {
            homeworldButton.setEnabled(true);
            homeworldButton.setOnClickListener(v -> {
                StarshipDetailsFragmentDirections.ActionStarshipDetailsFragmentToPeopleListFragment action =
                        StarshipDetailsFragmentDirections.actionStarshipDetailsFragmentToPeopleListFragment(starship.getPilotIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }
    }
}