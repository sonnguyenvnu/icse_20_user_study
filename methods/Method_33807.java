private void initToolBar(){
  setSupportActionBar(mTitleToolBar);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayShowTitleEnabled(false);
  }
  mTitleToolBar.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.actionbar_more));
  tvGunTitle.postDelayed(() -> tvGunTitle.setSelected(true),1900);
  tvGunTitle.setText(mTitle);
}
