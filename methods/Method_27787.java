@Override public void onItemLongClick(int position,View v,Gist item){
  if (getView() != null) {
    getView().onDeletePinnedGist(item.getId(),position);
  }
}
