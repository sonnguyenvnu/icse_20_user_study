public void setDrawerHeaderBackground(){
  String path1=mainActivity.getPrefs().getString(PreferencesConstants.PREFERENCE_DRAWER_HEADER_PATH,null);
  if (path1 == null) {
    return;
  }
  try {
    final ImageView headerImageView=new ImageView(mainActivity);
    headerImageView.setImageDrawable(drawerHeaderParent.getBackground());
    mImageLoader.get(path1,new ImageLoader.ImageListener(){
      @Override public void onResponse(      ImageLoader.ImageContainer response,      boolean isImmediate){
        headerImageView.setImageBitmap(response.getBitmap());
        drawerHeaderView.setBackgroundResource(R.drawable.amaze_header_2);
      }
      @Override public void onErrorResponse(      VolleyError error){
      }
    }
);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
