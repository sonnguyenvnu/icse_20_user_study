@NavigationItem private int getNavigationItemSelected(@IdRes int viewId){
switch (viewId) {
case R.id.navigation_item_albums:
    return NAVIGATION_ITEM_ALL_ALBUMS;
case R.id.navigation_item_all_media:
  return NAVIGATION_ITEM_ALL_MEDIA;
case R.id.navigation_item_timeline:
return NAVIGATION_ITEM_TIMELINE;
case R.id.navigation_item_hidden_albums:
return NAVIGATION_ITEM_HIDDEN_FOLDERS;
case R.id.navigation_item_wallpapers:
return NAVIGATION_ITEM_WALLPAPERS;
case R.id.navigation_item_donate:
return NAVIGATION_ITEM_DONATE;
case R.id.navigation_item_settings:
return NAVIGATION_ITEM_SETTINGS;
case R.id.navigation_item_affix:
return NAVIGATION_ITEM_AFFIX;
case R.id.navigation_item_about:
return NAVIGATION_ITEM_ABOUT;
}
return NAVIGATION_ITEM_ABOUT;
}
