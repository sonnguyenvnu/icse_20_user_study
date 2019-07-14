@OnClick(R.id.search) void onSearchClicked(){
  getPresenter().onSearchClicked(pager,searchEditText);
}
