@Override public void onLabelAdded(@NonNull LabelModel labelModel){
  adapter.addItem(labelModel,0);
  recycler.scrollToPosition(0);
}
