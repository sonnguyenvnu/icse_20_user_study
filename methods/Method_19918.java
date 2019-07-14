/** 
 * Record a screen view for the visible  {@link ImageFragment} displayedinside  {@link FragmentPagerAdapter}.
 */
private void recordImageView(){
  String id=getCurrentImageId();
  String name=getCurrentImageTitle();
  Bundle bundle=new Bundle();
  bundle.putString(FirebaseAnalytics.Param.ITEM_ID,id);
  bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,name);
  bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"image");
  mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);
}
