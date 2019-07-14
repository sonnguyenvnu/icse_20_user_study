public int findPositionById(long id){
  int count=getItemCount();
  for (int i=0; i < count; ++i) {
    if (getItemId(i) == id) {
      return i;
    }
  }
  return RecyclerView.NO_POSITION;
}
