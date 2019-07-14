public void removeHeaderView(){
  if (mHeaderView != null) {
    this.mHeaderView=null;
    this.notifyDataSetChanged();
  }
}
