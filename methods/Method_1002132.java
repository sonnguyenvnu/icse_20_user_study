/** 
 * @return whether to continue sending reports to this {@link LocationRequestHelper}
 */
public boolean report(Location location){
  if (location == null)   return true;
  if (lastReport != null) {
    if (location.getTime() - lastReport.getTime() < locationRequest.getFastestInterval()) {
      return true;
    }
    if (location.distanceTo(lastReport) < locationRequest.getSmallestDesplacement()) {
      return true;
    }
  }
  lastReport=new Location(location);
  lastReport.setProvider("fused");
  Log.d(TAG,"sending Location: " + location);
  if (listener != null) {
    try {
      listener.onLocationChanged(lastReport);
    }
 catch (    RemoteException e) {
      return false;
    }
  }
 else   if (pendingIntent != null) {
    Intent intent=new Intent();
    intent.putExtra("com.google.android.location.LOCATION",lastReport);
    try {
      pendingIntent.send(context,0,intent);
    }
 catch (    PendingIntent.CanceledException e) {
      return false;
    }
  }
 else   if (callback != null) {
    try {
      callback.onLocationResult(LocationResult.create(Arrays.asList(lastReport)));
    }
 catch (    RemoteException e) {
      return false;
    }
  }
  numReports++;
  return numReports < locationRequest.getNumUpdates();
}
