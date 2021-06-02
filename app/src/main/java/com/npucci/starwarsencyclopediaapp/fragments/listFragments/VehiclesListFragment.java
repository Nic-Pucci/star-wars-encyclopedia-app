package com.npucci.starwarsencyclopediaapp.fragments.listFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.npucci.starwarsencyclopediaapp.R;
import com.npucci.starwarsencyclopediaapp.pojos.Vehicle;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.VehiclesListViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class VehiclesListFragment extends Fragment {
    private VehiclesListViewModel vehiclesListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vehiclesListViewModel = new ViewModelProvider(this).get(VehiclesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vehicles_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        final ListView vehiclesListView = view.findViewById(R.id.vehicle_list_view);

        VehiclesListFragmentArgs args = VehiclesListFragmentArgs.fromBundle(getArguments());
        int[] vehicleIDs = args.getVehicleIDs();
        vehiclesListViewModel.updateDataResultsLive(vehicleIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Vehicle> vehiclesList = dataResults.getResults();

            List<String> vehicleDisplayStrings = vehiclesList.stream().map(vehicle -> vehicle.getName()).collect(Collectors.toList());
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, vehicleDisplayStrings);
            vehiclesListView.setAdapter(arrayAdapter);

            vehiclesListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Vehicle selectedVehicle = vehiclesList.get(index);

                VehiclesListFragmentDirections.ActionVehiclesListFragmentToVehicleDetailsFragment action =
                        VehiclesListFragmentDirections.actionVehiclesListFragmentToVehicleDetailsFragment(selectedVehicle);
                NavControllerUtil.navigate(navController, action);
            });
        });

        vehiclesListViewModel.getErrorLive().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        });
    }
}