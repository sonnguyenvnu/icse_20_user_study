public void setIsUpdating(final boolean value){
  AndroidUtilities.runOnUIThread(() -> {
    if (isUpdating == value) {
      return;
    }
    isUpdating=value;
    if (connectionState == ConnectionStateConnected) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didUpdateConnectionState);
    }
  }
);
}
