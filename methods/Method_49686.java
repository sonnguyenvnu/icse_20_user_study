/** 
 * Determines whether one Location reading is better than the current Location fix. Copied from http://developer.android.com/guide/topics/location/strategies.html
 * @param location            The new Location that you want to evaluate
 * @param currentBestLocation The current Location fix, to which you want to compare the new one
 */
public static boolean isBetterLocation(Location location,Location currentBestLocation){
  if (currentBestLocation == null) {
    return true;
  }
  long timeDelta=location.getTime() - currentBestLocation.getTime();
  boolean isSignificantlyNewer=timeDelta > TWO_MINUTES;
  boolean isSignificantlyOlder=timeDelta < -TWO_MINUTES;
  boolean isNewer=timeDelta > 0;
  if (isSignificantlyNewer) {
    return true;
  }
 else   if (isSignificantlyOlder) {
    return false;
  }
  int accuracyDelta=(int)(location.getAccuracy() - currentBestLocation.getAccuracy());
  boolean isLessAccurate=accuracyDelta > 0;
  boolean isMoreAccurate=accuracyDelta < 0;
  boolean isSignificantlyLessAccurate=accuracyDelta > 200;
  boolean isFromSameProvider=isSameProvider(location.getProvider(),currentBestLocation.getProvider());
  if (isMoreAccurate) {
    return true;
  }
 else   if (isNewer && !isLessAccurate) {
    return true;
  }
 else   if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
    return true;
  }
  return false;
}
