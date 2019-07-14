@Override public void onItemLongClick(int position,View v,Issue item){
  if (getView() != null) {
    getView().onDeletePinnedIssue(item.getId(),position);
  }
}
