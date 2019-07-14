private void displayAlbums(boolean hidden){
  fragmentMode=FragmentMode.MODE_ALBUMS;
  unlockNavigationDrawer();
  if (albumsFragment == null)   initAlbumsFragment();
  albumsFragment.displayAlbums(hidden);
  setContentFragment();
}
