@OnClick(R.id.ll_map_provider) public void onMapProviderClicked(View view){
  new MapProviderSetting(SettingsActivity.this).choseProvider();
}
