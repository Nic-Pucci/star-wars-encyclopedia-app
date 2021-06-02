package com.npucci.starwarsencyclopediaapp.pojos;

import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.npucci.starwarsencyclopediaapp.repositories.SWAPIRepository;

import java.io.Serializable;

public abstract class SWAPIData implements Serializable {
    public static final int UNKNOWN_ID = -1;

    private String url = "";
    private String created = "";
    private String edited = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getID(){
        int id = getIDFromUrl(url);
        return id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    protected int[] extractIDsFromUrls(String...urlsWithIDs) {
        if(urlsWithIDs == null || urlsWithIDs.length == 0) {
            return new int[0];
        }

        int[] extractedIDs = new int[urlsWithIDs.length];
        for(int i = 0; i < urlsWithIDs.length; i++){
            String urlWithID = urlsWithIDs[i];
            int id = getIDFromUrl(urlWithID);
            extractedIDs[i] = id;
        }

        return extractedIDs;
    }

    protected int getIDFromUrl(String url) {
        if(TextUtils.isEmpty(url)) {
            return UNKNOWN_ID;
        }

        Uri uri = Uri.parse(url);
        String idSegment = uri.getLastPathSegment();

        if(!TextUtils.isDigitsOnly(idSegment)){
            return UNKNOWN_ID;
        }

        int id = Integer.parseInt(idSegment);
        return id;
    }

    public abstract String getDisplayName();

    public abstract String getSortValue();
}
