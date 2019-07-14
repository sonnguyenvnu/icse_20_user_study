protected void onItemWriteStarted(int position){
  mItemAdapter.notifyItemChanged(position);
}
