package com.marvelcomics.brito.marvelcomics.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import com.marvelcomics.brito.entity.CharacterEntity;
import com.marvelcomics.brito.marvelcomics.R;
import com.marvelcomics.brito.marvelcomics.databinding.ActivityHomeBinding;
import com.marvelcomics.brito.marvelcomics.ui.fragment.CharacterFragment;
import com.marvelcomics.brito.marvelcomics.ui.fragment.comics.ComicsFragment;
import com.marvelcomics.brito.presentation.ResourceModel;
import com.marvelcomics.brito.presentation.viewmodel.home.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Inject
    protected HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setViewActivity(this);
        initListeners();
    }

    public void searchViewFocus() {
        binding.searchviewMarvelCharacter.setIconified(false);
    }

    private void initListeners() {
        binding.searchviewMarvelCharacter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                observeCharacter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void observeCharacter(String name) {
        homeViewModel.loadCharacters(name).observe(this, listResourceModel -> {
            if (listResourceModel != null) {
                handleCharactersResult(listResourceModel);
            }
        });
    }

    private void handleCharactersResult(ResourceModel<List<CharacterEntity>> listResourceModel) {
        switch (listResourceModel.getState()) {
            case LOADING:
                break;
            case SUCCESS:
                List<CharacterEntity> characterResource = listResourceModel.getData();
                if (!characterResource.isEmpty()) {
                    instantiateCharacterFragment(characterResource.get(0));
                    instantiateComicsFragment(characterResource.get(0).getId());
                    instantiateSeriesFragment();
                } else {
                    //TODO DialogAlert
                }
                break;
            case ERROR:
                //TODO DialogAlert
                //binding.textviewMarvelCharacterResult.setText("Error Marvel API: " + listResourceModel.getMessage());
                break;
            default:
                // do nothing
        }
    }

    private void instantiateCharacterFragment(CharacterEntity characterEntity) {
        CharacterFragment fragment = CharacterFragment.newInstance(characterEntity);
        commitFragment(R.id.fragment_home_character, fragment);
    }

    private void instantiateComicsFragment(int id) {
        ComicsFragment fragment = ComicsFragment.newInstance(id);
        commitFragment(R.id.fragment_home_comics, fragment);
    }

    private void instantiateSeriesFragment() {

    }

    private void commitFragment(int containerId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerId,
                fragment, fragment.getClass().getSimpleName()).commit();
    }
}
