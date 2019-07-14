@Override public void onItemLongClick(int position,View v,PullRequest item){
  if (getView() != null) {
    getView().onDeletePinnedPullRequest(item.getId(),position);
  }
}
