public void addHeaderView(View header){
  if (header == null) {
    Log.e(TAG,"header is null!!!");
    return;
  }
  this.mHeaderView=header;
  this.notifyDataSetChanged();
}
