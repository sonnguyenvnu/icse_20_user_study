public void goBackToAlbums(){
  unreferenceFragments();
  fragmentMode=FragmentMode.MODE_ALBUMS;
  unlockNavigationDrawer();
  getSupportFragmentManager().popBackStack();
  albumsFragment=(AlbumsFragment)getSupportFragmentManager().findFragmentByTag(AlbumsFragment.TAG);
  selectNavigationItem(NAVIGATION_ITEM_ALL_ALBUMS);
  showDefaultToolbar();
}
