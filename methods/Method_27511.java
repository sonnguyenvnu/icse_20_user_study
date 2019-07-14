protected void setToolbarIcon(@DrawableRes int res){
  if (getSupportActionBar() != null) {
    getSupportActionBar().setHomeAsUpIndicator(res);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
