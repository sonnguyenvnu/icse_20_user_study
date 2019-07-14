@Override public void onItemLongClick(int position,View v,PinnedRepos item){
  if (getView() != null) {
    if (item.getRepoFullName().equalsIgnoreCase("k0shk0sh/FastHub")) {
      return;
    }
    getView().onDeletePinnedRepo(item.getId(),position);
  }
}
