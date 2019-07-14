/** 
 * Call this method when you need to update the MainActivity view components' colors based on update in the  {@link MainActivity#currentTab}Warning - All the variables should be initialised before calling this method!
 */
public void updateViews(ColorDrawable colorDrawable){
  appbar.getBottomBar().setBackgroundColor(colorDrawable.getColor());
  mainActivity.getSupportActionBar().setBackgroundDrawable(colorDrawable);
  drawer.setBackgroundColor(colorDrawable.getColor());
  if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    mainActivity.getWindow().setStatusBarColor(colorDrawable.getColor());
    if (getBoolean(PREFERENCE_COLORED_NAVIGATION))     mainActivity.getWindow().setNavigationBarColor(PreferenceUtils.getStatusColor(colorDrawable.getColor()));
  }
 else   if (SDK_INT == Build.VERSION_CODES.KITKAT_WATCH || SDK_INT == Build.VERSION_CODES.KITKAT) {
    SystemBarTintManager tintManager=new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setStatusBarTintColor(colorDrawable.getColor());
  }
}
