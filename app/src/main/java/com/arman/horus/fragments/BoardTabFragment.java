package com.arman.horus.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arman.horus.R;
import com.arman.horus.adapters.CardItemsAdapter;
import com.arman.horus.listeners.OnCardClickListener;
import com.arman.horus.models.CardItem;

import java.util.List;

public class BoardTabFragment extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tab_board, container, false);
        addPlacesListToBoard();
        addTripsListToBoard();
        return mView;
    }

    private void addPlacesListToBoard() {
        RecyclerView placesRecyclerView = (RecyclerView) mView.findViewById(R.id.places);
        placesRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        placesRecyclerView.setLayoutManager(layoutManager);
        List<CardItem> cardItems = CardItem.generateItems();
        RecyclerView.Adapter cardsAdapter = new CardItemsAdapter(cardItems, new OnCardClickListener("place", getContext()));
        placesRecyclerView.setAdapter(cardsAdapter);
    }

    private void addTripsListToBoard() {
        RecyclerView tripsRecyclerView = (RecyclerView) mView.findViewById(R.id.trips);
        tripsRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        tripsRecyclerView.setLayoutManager(layoutManager);
        List<CardItem> cardItems = CardItem.generateItems();
        RecyclerView.Adapter cardsAdapter = new CardItemsAdapter(cardItems, new OnCardClickListener("trip", getContext()));
        tripsRecyclerView.setAdapter(cardsAdapter);
    }

}
