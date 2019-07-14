private void loadFolders(int type){
  typeExcluded=type == HandlingAlbums.EXCLUDED;
  folders=HandlingAlbums.getInstance(getApplicationContext()).getFolders(type);
  checkNothing();
  if (isExcludedMode())   setTitle(getString(R.string.excluded_items));
 else   setTitle(getString(R.string.white_list));
  adapter.notifyDataSetChanged();
  supportInvalidateOptionsMenu();
}
