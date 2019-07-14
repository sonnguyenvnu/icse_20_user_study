private void getIntentData(){
  if (getIntent() != null) {
    mTitle=getIntent().getStringExtra("mTitle");
    mUrl=getIntent().getStringExtra("mUrl");
    isTitleFix=getIntent().getBooleanExtra("isTitleFix",false);
  }
}
