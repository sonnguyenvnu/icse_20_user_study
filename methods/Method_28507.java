public void removeItems(@NonNull List<M> items){
  int prevSize=getItemCount();
  data.removeAll(items);
  notifyItemRangeRemoved(prevSize,Math.abs(data.size() - prevSize));
}
