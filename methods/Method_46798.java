void initialiseViews(){
  appBarLayout=getAppbar().getAppbarLayout();
  setSupportActionBar(getAppbar().getToolbar());
  drawer=new Drawer(this);
  indicator_layout=findViewById(R.id.indicator_layout);
  getSupportActionBar().setDisplayShowTitleEnabled(false);
  fabBgView=findViewById(R.id.fab_bg);
switch (getAppTheme().getSimpleTheme()) {
case DARK:
    fabBgView.setBackgroundResource(R.drawable.fab_shadow_dark);
  break;
case BLACK:
fabBgView.setBackgroundResource(R.drawable.fab_shadow_black);
break;
}
fabBgView.setOnClickListener(view -> {
if (getAppbar().getSearchView().isEnabled()) getAppbar().getSearchView().hideSearchView();
}
);
drawer.setDrawerHeaderBackground();
if (SDK_INT == Build.VERSION_CODES.KITKAT_WATCH || SDK_INT == Build.VERSION_CODES.KITKAT) {
SystemBarTintManager tintManager=new SystemBarTintManager(this);
tintManager.setStatusBarTintEnabled(true);
FrameLayout.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)findViewById(R.id.drawer_layout).getLayoutParams();
SystemBarTintManager.SystemBarConfig config=tintManager.getConfig();
if (!drawer.isLocked()) p.setMargins(0,config.getStatusBarHeight(),0,0);
}
 else if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
Window window=getWindow();
window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
if (drawer.isLocked()) {
window.setStatusBarColor((skinStatusBar));
}
 else window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
if (getBoolean(PREFERENCE_COLORED_NAVIGATION)) window.setNavigationBarColor(skinStatusBar);
}
}
