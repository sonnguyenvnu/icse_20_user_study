public static void unsubscribe(){
  if (lm != null) {
    lm.removeUpdates(locationListenerGps);
    lm.removeUpdates(locationListenerNetwork);
  }
}
