@Override public void onItemClick(int position,View v,RepoFile item){
  if (getView() == null)   return;
  if (v.getId() != R.id.menu) {
    getView().onItemClicked(item);
  }
 else {
    getView().onMenuClicked(position,item,v);
  }
}
