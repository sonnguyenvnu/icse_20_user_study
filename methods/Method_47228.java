private void setNavViewDimension(CustomNavigationView navView){
  int screenWidth=AppConfig.getInstance().getScreenUtils().getScreenWidthInDp();
  int desiredWidthInDp=screenWidth - ScreenUtils.TOOLBAR_HEIGHT_IN_DP;
  int desiredWidthInPx=AppConfig.getInstance().getScreenUtils().convertDbToPx(desiredWidthInDp);
  navView.setLayoutParams(new DrawerLayout.LayoutParams(desiredWidthInPx,LinearLayout.LayoutParams.MATCH_PARENT,Gravity.START));
}
