@Override public void onPageSelected(int p1){
  mainActivity.getAppbar().getAppbarLayout().animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
  MainActivity.currentTab=p1;
  if (sharedPrefs != null) {
    sharedPrefs.edit().putInt(PreferencesConstants.PREFERENCE_CURRENT_TAB,MainActivity.currentTab).commit();
  }
  Log.d(getClass().getSimpleName(),"Page Selected: " + MainActivity.currentTab);
  Fragment fragment=fragments.get(p1);
  if (fragment != null && fragment instanceof MainFragment) {
    MainFragment ma=(MainFragment)fragment;
    if (ma.getCurrentPath() != null) {
      mainActivity.getDrawer().selectCorrectDrawerItemForPath(ma.getCurrentPath());
      mainActivity.getAppbar().getBottomBar().updatePath(ma.getCurrentPath(),ma.results,MainActivityHelper.SEARCH_TEXT,ma.openMode,ma.folder_count,ma.file_count,ma);
    }
  }
  if (circleDrawable1 != null && circleDrawable2 != null)   updateIndicator(p1);
}
