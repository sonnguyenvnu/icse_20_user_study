@OnClick(R.id.toParentFolder) void onBackClicked(){
  if (adapter.getItemCount() > 0) {
    adapter.clear();
    getRepoFilesView().onSetData(getPresenter().getLogin(),getPresenter().getRepoId(),"",ref,false,null);
  }
}
