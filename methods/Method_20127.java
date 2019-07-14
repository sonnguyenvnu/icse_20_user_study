@Override public void onViewRecycled(EpoxyViewHolder holder){
  viewHolderState.save(holder);
  boundViewHolders.remove(holder);
  EpoxyModel<?> model=holder.getModel();
  holder.unbind();
  onModelUnbound(holder,model);
}
