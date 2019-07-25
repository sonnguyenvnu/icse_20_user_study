public BaseRecyclerAdapter delete(int pos){
  mData.remove(pos);
  notifyItemRemoved(pos);
  return this;
}
