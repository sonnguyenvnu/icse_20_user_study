@OnClick(R.id.searchRepoFiles) void onSearchClicked(){
  startActivity(SearchFileActivity.createIntent(getContext(),getPresenter().getLogin(),getPresenter().getRepoId(),isEnterprise()));
}
