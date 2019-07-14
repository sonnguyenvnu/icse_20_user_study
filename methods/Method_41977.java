private void showDefaultToolbar(){
  updateToolbar(getString(R.string.app_name),GoogleMaterial.Icon.gmd_menu,v -> navigationDrawer.openDrawer(GravityCompat.START));
}
