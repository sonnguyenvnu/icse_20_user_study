/** 
 * ????????
 * @param activity       ????? activity
 * @param statusBarAlpha ???
 */
private static void addTranslucentView(Activity activity,int statusBarAlpha){
  ViewGroup contentView=(ViewGroup)activity.findViewById(android.R.id.content);
  if (contentView.getChildCount() > 1) {
    contentView.getChildAt(1).setBackgroundColor(Color.argb(statusBarAlpha,0,0,0));
  }
 else {
    contentView.addView(createTranslucentStatusBarView(activity,statusBarAlpha));
  }
}
