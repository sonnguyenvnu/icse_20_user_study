protected void onItemWriteFinished(int position){
  mItemAdapter.notifyItemChanged(position);
}
