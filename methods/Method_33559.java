/** 
 * toolbar??
 */
private void setTitleBar(){
  setSupportActionBar(binding.titleToolBar);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
  }
  binding.titleToolBar.setTitleTextAppearance(this,R.style.ToolBar_Title);
  binding.titleToolBar.setSubtitleTextAppearance(this,R.style.Toolbar_SubTitle);
  binding.titleToolBar.setTitle(subjectsBean.getTitle());
  binding.titleToolBar.setSubtitle(String.format("???%s",StringFormatUtil.formatName(subjectsBean.getCasts())));
  binding.titleToolBar.inflateMenu(R.menu.movie_detail);
  binding.titleToolBar.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.actionbar_more));
  binding.titleToolBar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      onBackPressed();
    }
  }
);
  binding.titleToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
    @Override public boolean onMenuItemClick(    MenuItem item){
switch (item.getItemId()) {
case R.id.actionbar_more:
        WebViewActivity.loadUrl(MovieDetailActivity.this,mMoreUrl,mMovieName);
      break;
  }
  return false;
}
}
);
}
