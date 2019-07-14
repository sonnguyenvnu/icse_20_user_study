public void notifyItemChangedById(long id){
  int position=findPositionById(id);
  if (position != RecyclerView.NO_POSITION) {
    notifyItemChanged(position);
  }
}
