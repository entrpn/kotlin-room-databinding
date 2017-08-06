package com.entrpn.room.livedata.example.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.io.Serializable;
import java.util.List;

public class PeopleTransactions implements Serializable {

    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    public static final String TAG = "people_transactions";

    @Embedded
    public PeopleModel peopleModel;

    @Relation(parentColumn = "mail", entityColumn = "userEmail")
    public List<PictureModel> pictures;
    @Relation(parentColumn = "mail", entityColumn = "userEmail")
    public List<NameModel> names;
    @Relation(parentColumn = "mail", entityColumn = "userEmail")
    public List<LoginModel> logins;
    @Relation(parentColumn = "mail", entityColumn = "userEmail")
    public List<LocationModel> locations;


    /*//////////////////////////////////////////////////////////
    // PUBLIC METHODS
    *///////////////////////////////////////////////////////////
    public String getThumbnail() {
        String retval = "";
        if (pictures != null && pictures.size() > 0) {
            retval = pictures.get(0).getThumbnail();
        }
        return retval;
    }
    public String getMediumImage() {
        String retval = "";
        if (pictures != null && pictures.size() > 0) {
            retval = pictures.get(0).getMedium();
        }
        return retval;
    }
    public String getLargeImage() {
        String retval = "";
        if (pictures != null && pictures.size() > 0) {
            retval = pictures.get(0).getLarge();
        }
        return retval;
    }
    public String getFullName() {
        String retval = "";
        if (names != null && names.size() > 0) {
            retval = names.get(0).getFullName();
        }
        return retval;
    }
    public String getUserName() {
        String retval = "";
        if (logins != null && logins.size() > 0) {
            retval = logins.get(0).getUserName();
        }
        return retval;
    }
    public String getAddress() {
        StringBuilder retval = new StringBuilder();
        if (locations != null && locations.size() > 0) {
            LocationModel model = locations.get(0);
            retval.append(model.getStreet()).append("\n")
                    .append(model.getCity()).append(", ")
                    .append(model.getState()).append(" ")
                    .append(model.getZip());
        }
        return retval.toString();
    }

}
