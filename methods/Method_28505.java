public void insertItems(@NonNull List<M> items){
  data.clear();
  data.addAll(items);
  notifyDataSetChanged();
  progressAdded=false;
}
