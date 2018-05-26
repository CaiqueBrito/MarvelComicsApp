package com.marvelcomics.brito.marvelcomics.ui.fragment.comics;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvelcomics.brito.entity.ComicEntity;
import com.marvelcomics.brito.infrastructure.utils.AlertDialogUtils;
import com.marvelcomics.brito.marvelcomics.R;
import com.marvelcomics.brito.marvelcomics.databinding.FragmentComicsBinding;
import com.marvelcomics.brito.marvelcomics.ui.fragment.ItemOffSetDecorationHorizontal;
import com.marvelcomics.brito.presentation.presenter.comics.ComicsContract;
import com.marvelcomics.brito.presentation.presenter.comics.ComicsPresenter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ComicsFragment extends Fragment implements ComicsContract.View {

    private static final String ARGUMENT_CHARACTER_ID = "character_id_args";
    private int characterId;

    @Inject
    protected ComicsPresenter comicsPresenter;

    private FragmentComicsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        characterId = args.getInt(ARGUMENT_CHARACTER_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        AndroidSupportInjection.inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comics, container, false);
        comicsPresenter.setView(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComics();
    }

    @Override
    public void showComics(List<ComicEntity> comics) {
        createdAdapter(comics);
    }

    @Override
    public void showError(String message) {
        AlertDialogUtils.showSimpleDialog("Erro", message, getContext());
    }

    private void loadComics() {
        comicsPresenter.loadComics(characterId);
    }

    private void createdAdapter(List<ComicEntity> comicEntities) {
        ComicsAdapter adapter = new ComicsAdapter(comicEntities);
        binding.recyclerviewFragmentComic.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerviewFragmentComic.setAdapter(adapter);
        binding.recyclerviewFragmentComic.addItemDecoration(new ItemOffSetDecorationHorizontal(8));
    }

    public static ComicsFragment newInstance(int characterId) {
        ComicsFragment fragment = new ComicsFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_CHARACTER_ID, characterId);

        fragment.setArguments(args);
        return fragment;
    }
}