@Override public void onChanged(int position,int count){
  mAdapter.notifyItemRangeChanged(position,count);
}
