public static void onConnectionStateChanged(final int state,final int currentAccount){
  AndroidUtilities.runOnUIThread(() -> {
    getInstance(currentAccount).connectionState=state;
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didUpdateConnectionState);
  }
);
}
