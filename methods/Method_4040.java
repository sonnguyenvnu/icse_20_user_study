@Override public void onInserted(int position,int count){
  mAdapter.notifyItemRangeInserted(position,count);
}
