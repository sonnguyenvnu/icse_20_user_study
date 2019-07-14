public void onItemSelected(@NavigationItem int navigationItemSelected){
  closeDrawer();
switch (navigationItemSelected) {
case NAVIGATION_ITEM_ALL_ALBUMS:
    displayAlbums(false);
  selectNavigationItem(navigationItemSelected);
break;
case NAVIGATION_ITEM_ALL_MEDIA:
displayMedia(Album.getAllMediaAlbum());
break;
case NAVIGATION_ITEM_TIMELINE:
displayTimeline(Album.getAllMediaAlbum());
selectNavigationItem(navigationItemSelected);
break;
case NAVIGATION_ITEM_HIDDEN_FOLDERS:
if (Security.isPasswordOnHidden()) {
askPassword();
}
 else {
selectNavigationItem(navigationItemSelected);
displayAlbums(true);
}
break;
case NAVIGATION_ITEM_WALLPAPERS:
Toast.makeText(MainActivity.this,"Coming Soon!",Toast.LENGTH_SHORT).show();
break;
case NAVIGATION_ITEM_DONATE:
DonateActivity.startActivity(this);
break;
case NavigationDrawer.NAVIGATION_ITEM_AFFIX:
Intent i=new Intent(getBaseContext(),AffixActivity.class);
startActivity(i);
break;
case NAVIGATION_ITEM_SETTINGS:
SettingsActivity.startActivity(this);
break;
case NAVIGATION_ITEM_ABOUT:
AboutActivity.startActivity(this);
break;
}
}
