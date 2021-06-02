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
import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;

public class VehicleDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vehicle_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        VehicleDetailsFragmentArgs args = VehicleDetailsFragmentArgs.fromBundle(getArguments());
        Vehicle vehicle = args.getVehicle();

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(vehicle.getName());

        TextView modelTextView = view.findViewById(R.id.model_text_view);
        modelTextView.setText("Model: " + vehicle.getModel());

        TextView vehicleClassTextView = view.findViewById(R.id.vehicle_class_text_view);
        vehicleClassTextView.setText("Vehicle Class: " + vehicle.getVehicleClass());

        TextView manufacturerTextView = view.findViewById(R.id.manufacturer_text_view);
        manufacturerTextView.setText("Manufacturer: " + vehicle.getManufacturer());

        TextView costInCreditsTextView = view.findViewById(R.id.cost_in_credits_text_view);
        costInCreditsTextView.setText("Cost in Credits: " + vehicle.getCostInCredits());

        TextView lengthTextView = view.findViewById(R.id.length_text_view);
        lengthTextView.setText("Length: " + vehicle.getLength());

        TextView passengersTextView = view.findViewById(R.id.passengers_text_view);
        passengersTextView.setText("Passengers: " + vehicle.getPassengers());

        TextView maxAtmospheringSpeedTextView = view.findViewById(R.id.max_atmosphering_speed_text_view);
        maxAtmospheringSpeedTextView.setText("Max Atmosphering Speed: " + vehicle.getMaxAtmospheringSpeed());

        TextView cargoCapacityTextView = view.findViewById(R.id.cargo_capacity_text_view);
        cargoCapacityTextView.setText("Cargo Capacity: " + vehicle.getCargoCapacity());

        TextView consumablesTextView = view.findViewById(R.id.consumables_text_view);
        consumablesTextView.setText("Consumables: " + vehicle.getConsumables());

        TextView createdDateTextView = view.findViewById(R.id.created_date_text_view);
        createdDateTextView.setText("Created Date: " + vehicle.getCreated());

        TextView editedDateTextView = view.findViewById(R.id.edited_date_text_view);
        editedDateTextView.setText("Edited Date: " + vehicle.getEdited());

        Button filmsButton = view.findViewById(R.id.films_button);
        if(vehicle.getFilmIDs().length == 0) {
            filmsButton.setEnabled(false);
        }
        else {
            filmsButton.setEnabled(true);
            filmsButton.setOnClickListener(v -> {
                VehicleDetailsFragmentDirections.ActionVehicleDetailsFragmentToFilmsListFragment action =
                        VehicleDetailsFragmentDirections.actionVehicleDetailsFragmentToFilmsListFragment(vehicle.getFilmIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }

        Button homeworldButton = view.findViewById(R.id.pilots_button);
        if(vehicle.getPilotIDs().length == 0) {
            homeworldButton.setEnabled(false);
        }
        else {
            homeworldButton.setEnabled(true);
            homeworldButton.setOnClickListener(v -> {
                VehicleDetailsFragmentDirections.ActionVehicleDetailsFragmentToPeopleListFragment action =
                        VehicleDetailsFragmentDirections.actionVehicleDetailsFragmentToPeopleListFragment(vehicle.getPilotIDs());
                NavControllerUtil.navigate(navController, action);
            });
        }
    }
}