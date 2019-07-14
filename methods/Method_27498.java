@Override public void onOpenSettings(){
  startActivityForResult(new Intent(this,SettingsActivity.class),BundleConstant.REFRESH_CODE);
}
