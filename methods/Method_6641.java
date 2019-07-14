public static int getLocationsCount(){
  int count=0;
  for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
    count+=LocationController.getInstance(a).sharingLocationsUI.size();
  }
  return count;
}
