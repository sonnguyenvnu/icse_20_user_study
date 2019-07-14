public void replace(@NonNull Collection<? extends T> collection){
  mList.clear();
  mList.addAll(collection);
  notifyDataSetChanged();
}
