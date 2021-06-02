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
import com.npucci.starwarsencyclopediaapp.pojos.Person;
import com.npucci.starwarsencyclopediaapp.utilities.NavControllerUtil;
import com.npucci.starwarsencyclopediaapp.viewmodels.dataListViewModel.CharactersListViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class CharactersListFragment extends Fragment {
    private CharactersListViewModel charactersListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        charactersListViewModel = new ViewModelProvider(this).get(CharactersListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.characters_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        final ListView charactersListView = view.findViewById(R.id.characters_list_view);

        CharactersListFragmentArgs args = CharactersListFragmentArgs.fromBundle(getArguments());
        int[] characterIDs = args.getCharacterIDs();

        charactersListViewModel.updateDataResultsLive(characterIDs).observe(getViewLifecycleOwner(), dataResults -> {
            List<Person> charactersList = dataResults.getResults();

            List<String> characterDisplayStrings = charactersList.stream().map(person -> person.getName()).collect(Collectors.toList());
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, characterDisplayStrings);
            charactersListView.setAdapter(arrayAdapter);

            charactersListView.setOnItemClickListener((adapterView, itemView, index, l) -> {
                Person selectedPerson = charactersList.get(index);

                CharactersListFragmentDirections.ActionCharactersListFragmentToPersonDetailsFragment action =
                        CharactersListFragmentDirections.actionCharactersListFragmentToPersonDetailsFragment(selectedPerson);
                NavControllerUtil.navigate(navController, action);
            });
        });
    }
}