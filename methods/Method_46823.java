public void invalidateNavBar(){
  @ColorInt int primaryColor=ColorPreferenceHelper.getPrimary(getCurrentColorPreference(),MainActivity.currentTab);
  if (SDK_INT == 20 || SDK_INT == 19) {
    SystemBarTintManager tintManager=new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setStatusBarTintColor(primaryColor);
    FrameLayout.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)findViewById(R.id.preferences).getLayoutParams();
    SystemBarTintManager.SystemBarConfig config=tintManager.getConfig();
    p.setMargins(0,config.getStatusBarHeight(),0,0);
  }
 else   if (SDK_INT >= 21) {
    boolean colourednavigation=getBoolean(PreferencesConstants.PREFERENCE_COLORED_NAVIGATION);
    Window window=getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    int tabStatusColor=PreferenceUtils.getStatusColor(primaryColor);
    window.setStatusBarColor(tabStatusColor);
    if (colourednavigation) {
      window.setNavigationBarColor(tabStatusColor);
    }
 else     if (window.getNavigationBarColor() != Color.BLACK) {
      window.setNavigationBarColor(Color.BLACK);
    }
  }
  if (getAppTheme().equals(AppTheme.BLACK))   getWindow().getDecorView().setBackgroundColor(Utils.getColor(this,android.R.color.black));
}
