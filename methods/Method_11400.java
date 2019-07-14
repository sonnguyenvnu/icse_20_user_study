public void removeFooterView(View footer){
  if (mFooterView != null) {
    this.mFooterView=null;
    this.notifyDataSetChanged();
  }
}
