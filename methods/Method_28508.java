public void swapItem(@NonNull M model,int position){
  if (position != -1) {
    data.set(position,model);
    notifyItemChanged(position);
  }
}
