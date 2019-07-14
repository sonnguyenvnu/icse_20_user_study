public void subList(int fromPosition,int toPosition){
  if (data.isEmpty())   return;
  data.subList(fromPosition,toPosition).clear();
  notifyItemRangeRemoved(fromPosition,toPosition);
}
