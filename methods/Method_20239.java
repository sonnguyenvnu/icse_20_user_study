@Override protected void onSelectedChanged(@Nullable EpoxyViewHolder viewHolder,int actionState){
  super.onSelectedChanged(viewHolder,actionState);
  if (viewHolder != null) {
    EpoxyModel<?> model=viewHolder.getModel();
    if (!isTouchableModel(model)) {
      throw new IllegalStateException("A model was selected that is not a valid target: " + model.getClass());
    }
    markRecyclerViewHasSelection((RecyclerView)viewHolder.itemView.getParent());
    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      holderBeingSwiped=viewHolder;
      onSwipeStarted((T)model,viewHolder.itemView,viewHolder.getAdapterPosition());
    }
 else     if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
      holderBeingDragged=viewHolder;
      onDragStarted((T)model,viewHolder.itemView,viewHolder.getAdapterPosition());
    }
  }
 else   if (holderBeingDragged != null) {
    onDragReleased((T)holderBeingDragged.getModel(),holderBeingDragged.itemView);
    holderBeingDragged=null;
  }
 else   if (holderBeingSwiped != null) {
    onSwipeReleased((T)holderBeingSwiped.getModel(),holderBeingSwiped.itemView);
    holderBeingSwiped=null;
  }
}
