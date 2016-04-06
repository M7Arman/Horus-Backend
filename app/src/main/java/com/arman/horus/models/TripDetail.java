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
    public String description;
    //TODO: Change type to something like iDate
    public String startDate;
    public String from;
    public String to;
    public String id;

    public TripDetail(int images, String title, String description, String startDate, String from, String to, String id) {
        this.images = images;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.from = from;
        this.to = to;
        this.id = id;
    }

    public static TripDetail dummyTripDetail() {
        String description = "An indefinite article indicates that its noun is not a particular one (or ones) identifiable to the listener. It may be something that the speaker is mentioning for the first time, or its precise identity may be irrelevant or hypothetical, or the speaker may be making a general statement about any such thing. English uses a/an, from the Old English forms of the number 'one', as its primary indefinite article. The form an is used before words that begin with a vowel sound (even if spelled with an initial consonant, as in an hour), and a before words that begin with a consonant sound (even if spelled with a vowel, as in a European). In many languages, the form of the article may vary according to the gender, number, or case of its noun. In some languages the article may be the only indication of the case. Many languages do not use articles at all, and may use other ways of indicating old versus new information, such as topic–comment constructions.";
        return new TripDetail(R.drawable.trip1, "Trip to Maymekh", description, "15:32", "Tigran Mets 15/34", "Maymekh top", "item_1");
    }
}
