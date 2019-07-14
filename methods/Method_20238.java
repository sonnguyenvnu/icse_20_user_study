@Override protected void onSwiped(EpoxyViewHolder viewHolder,int direction){
  EpoxyModel<?> model=viewHolder.getModel();
  View view=viewHolder.itemView;
  int position=viewHolder.getAdapterPosition();
  if (!isTouchableModel(model)) {
    throw new IllegalStateException("A model was swiped that is not a valid target: " + model.getClass());
  }
  onSwipeCompleted((T)model,view,position,direction);
}
