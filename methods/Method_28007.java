@Override public void onItemClick(int position,View v,RepoFile item){
  if (!item.getPath().equalsIgnoreCase(path))   if (getView() != null)   getView().onItemClicked(item,position);
}
