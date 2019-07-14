private void setupUiForTimeline(){
  lockNavigationDrawer();
  updateToolbar(getString(R.string.timeline_toolbar_title),GoogleMaterial.Icon.gmd_arrow_back,v -> goBackToAlbums());
}
