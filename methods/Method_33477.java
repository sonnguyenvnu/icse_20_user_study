/** 
 * ??toolbar
 */
protected void setToolBar(){
  setSupportActionBar(bindingTitleView.tbBaseTitle);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
  }
  bindingTitleView.tbBaseTitle.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.actionbar_more));
  bindingTitleView.tbBaseTitle.setNavigationOnClickListener(v -> {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      finishAfterTransition();
    }
 else {
      finish();
    }
  }
);
  bindingTitleView.tbBaseTitle.setOnMenuItemClickListener(item -> {
    if (item.getItemId() == R.id.actionbar_more) {
      setTitleClickMore();
    }
    return false;
  }
);
}
