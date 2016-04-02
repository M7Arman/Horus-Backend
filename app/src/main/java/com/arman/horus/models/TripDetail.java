package com.arman.horus.models;

import com.arman.horus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arman on 4/2/16.
 */
public class TripDetail {

    public int images;
    public String title;
    //TODO: Change type to something like iDate
    public String startDate;
    public String from;
    public String to;
    public String id;

    public TripDetail(int images, String title, String startDate, String from, String to, String id) {
        this.images = images;
        this.title = title;
        this.startDate = startDate;
        this.from = from;
        this.to = to;
        this.id = id;
    }

    public static TripDetail dummyTripDetail() {
        return new TripDetail(R.drawable.trip1, "Trip to Maymekh", "15:32", "Tigran Mets 15/34", "Maymekh top", "item_1");
    }
}
