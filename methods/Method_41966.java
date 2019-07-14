@Override public void changedEditMode(boolean editMode,int selected,int total,@Nullable View.OnClickListener listener,@Nullable String title){
  if (editMode) {
    updateToolbar(getString(R.string.toolbar_selection_count,selected,total),GoogleMaterial.Icon.gmd_check,listener);
  }
 else   if (inAlbumMode()) {
    showDefaultToolbar();
  }
 else {
    updateToolbar(title,GoogleMaterial.Icon.gmd_arrow_back,v -> goBackToAlbums());
  }
}
