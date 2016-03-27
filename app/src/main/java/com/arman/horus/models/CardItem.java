package com.arman.horus.models;

import com.arman.horus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arman on 3/27/16.
 */
public class CardItem {private String title = "No Title";
    private int image;
    private int icon;

    public CardItem(String title, int image, int icon) {
        this.title = title;
        this.image = image;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public static List<CardItem> generateItems() {
        List<CardItem> cards = new ArrayList<>();
        cards.add(new CardItem("A title for card", R.drawable.trip, android.R.drawable.ic_lock_idle_lock));
        cards.add(new CardItem("This item has too very-very long and ugly title", R.drawable.trip, android.R.drawable.stat_sys_speakerphone));
        cards.add(new CardItem("Another long title for the third card", R.drawable.trip, android.R.drawable.btn_dialog));
        return cards;
    }
}
