package com.arman.horus.listeners;

import android.content.Context;
import android.content.Intent;

import com.arman.horus.activities.PlaceDetailActivity;
import com.arman.horus.adapters.CardItemsAdapter;
import com.arman.horus.models.CardItem;

/**
 * Created by arman on 3/29/16.
 */
public class OnPlaceCardClickListener implements CardItemsAdapter.OnItemClickListener {

    private final Context activity;

    public OnPlaceCardClickListener(Context activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(CardItem item) {
        Intent intent = new Intent(activity, PlaceDetailActivity.class);
        intent.putExtra(PlaceDetailActivity.ID, item.getId());
        activity.startActivity(intent);
    }
}
