private void initFabTitle(TitleFAB fabTitle,int type){
  int iconSkin=getCurrentColorPreference().iconSkin;
  fabTitle.setBackgroundColor(iconSkin);
  fabTitle.setRippleColor(Utils.getColor(this,R.color.white_translucent));
  fabTitle.setOnClickListener(view -> {
    mainActivityHelper.add(type);
    floatingActionButton.collapse();
  }
);
switch (getAppTheme().getSimpleTheme()) {
case DARK:
    fabTitle.setTitleBackgroundColor(Utils.getColor(this,R.color.holo_dark_background));
  fabTitle.setTitleTextColor(Utils.getColor(this,R.color.text_dark));
break;
case BLACK:
fabTitle.setTitleBackgroundColor(Color.BLACK);
fabTitle.setTitleTextColor(Utils.getColor(this,R.color.text_dark));
break;
}
}
