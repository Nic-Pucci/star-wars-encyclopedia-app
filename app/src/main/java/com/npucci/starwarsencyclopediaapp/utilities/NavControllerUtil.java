package com.npucci.starwarsencyclopediaapp.utilities;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;

import timber.log.Timber;

public class NavControllerUtil {
    public static void navigate(@NonNull NavController navController, int resID){
        try {
            navController.navigate(resID);
        }
        catch (IllegalArgumentException e) {
            Timber.e ( "Navigation button was either clicked twice on a graph change, or has legit wrong destination");
        }
        catch (Exception e) {
            Timber.e ( "Navigation button was either clicked twice on a graph change, or has legit wrong destination");
        }
    }

    public static void navigate(@NonNull NavController navController, NavDirections navDirections){
        try {
            navController.navigate(navDirections);
        }
        catch (IllegalArgumentException e) {
            Timber.e ( "Navigation button was either clicked twice on a graph change, or has legit wrong destination");
        }
        catch (Exception e) {
            Timber.e ( "Navigation button was either clicked twice on a graph change, or has legit wrong destination");
        }
    }
}