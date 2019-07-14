@Override protected int getMovementFlags(RecyclerView recyclerView,EpoxyViewHolder viewHolder){
  EpoxyModel<?> model=viewHolder.getModel();
  boolean isOtherCallbackActive=holderBeingDragged == null && holderBeingSwiped == null && recyclerViewHasSelection(recyclerView);
  if (!isOtherCallbackActive && isTouchableModel(model)) {
    return getMovementFlagsForModel((T)model,viewHolder.getAdapterPosition());
  }
 else {
    return 0;
  }
}
