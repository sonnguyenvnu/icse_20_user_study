@Override public void onItemClicked(@NonNull RepoFile model,int position){
  if (getRepoFilesView().isRefreshing())   return;
  getRepoFilesView().onSetData(getPresenter().getLogin(),getPresenter().getRepoId(),Objects.toString(model.getPath(),""),ref,false,null);
  if ((position + 1) < adapter.getItemCount()) {
    adapter.subList(position + 1,adapter.getItemCount());
  }
  recycler.scrollToPosition(adapter.getItemCount() - 1);
}
