@Override public void onItemClick(int position,View v,SearchCodeModel item){
  if (getView() != null) {
    getView().onItemClicked(item);
  }
}
