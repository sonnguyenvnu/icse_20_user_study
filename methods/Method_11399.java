public void addFooterView(View footer){
  if (footer == null) {
    Log.e(TAG,"footer is null!!!");
    return;
  }
  this.mFooterView=footer;
  this.notifyDataSetChanged();
}
