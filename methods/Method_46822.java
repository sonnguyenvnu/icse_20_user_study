public void invalidateToolbarColor(){
  @ColorInt int primaryColor=ColorPreferenceHelper.getPrimary(getCurrentColorPreference(),MainActivity.currentTab);
  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(primaryColor));
}
