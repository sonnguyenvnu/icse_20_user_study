/** 
 * This sample has a single Activity, so we need to manually record "screen views" as we change fragments.
 */
private void recordScreenView(){
  String screenName=getCurrentImageId() + "-" + getCurrentImageTitle();
  mFirebaseAnalytics.setCurrentScreen(this,screenName,null);
}
