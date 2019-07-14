private void setTitleBar(){
  setSupportActionBar(binding.titleToolBar);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
  }
  binding.tvTitle.setText(subjectsBean.getTitle());
  binding.tvSubtitle.setText("???" + StringFormatUtil.formatName(subjectsBean.getCasts()));
  binding.titleToolBar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      onBackPressed();
    }
  }
);
}
