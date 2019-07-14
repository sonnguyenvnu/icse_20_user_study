@Override protected boolean canDropOver(RecyclerView recyclerView,EpoxyViewHolder current,EpoxyViewHolder target){
  return isTouchableModel(target.getModel());
}
