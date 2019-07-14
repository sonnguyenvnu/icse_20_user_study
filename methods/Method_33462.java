/** 
 * ??titlebar
 */
protected void setToolBar(){
  setSupportActionBar(mBaseBinding.toolBar);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
  }
  mBaseBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        finishAfterTransition();
      }
 else {
        onBackPressed();
      }
    }
  }
);
}
