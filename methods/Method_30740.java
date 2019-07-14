protected void notifyDataChanged(){
  int newItemCount=getItemCount();
  if (newItemCount <= mBoundItemCount) {
    return;
  }
  notifyItemRangeInserted(mBoundItemCount,newItemCount - mBoundItemCount);
  mBoundItemCount=newItemCount;
}
