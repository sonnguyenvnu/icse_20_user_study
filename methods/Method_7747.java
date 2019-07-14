public boolean isDataSetChanged(){
  int current=currentCount;
  return current != getItemCount() || current == 1;
}
