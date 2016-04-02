package com.arman.horus.listeners;

import android.content.Context;
import android.content.Intent;

import com.arman.horus.activities.TripDetailActivity;
import com.arman.horus.adapters.CardItemsAdapter;
import com.arman.horus.models.CardItem;

/**
 * Created by arman on 3/29/16.
 */
public class OnTripCardClickListener implements CardItemsAdapter.OnItemClickListener {

    private final Context activity;

    public OnTripCardClickListener(Context activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(CardItem item) {
        Intent intent = new Intent(activity, TripDetailActivity.class);
        intent.putExtra(TripDetailActivity.ID, item.getId());
        activity.startActivity(intent);
    }
}
