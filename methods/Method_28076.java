@Override public void onToggleSelection(LabelModel labelModel,boolean select){
  if (select) {
    labelModels.add(labelModel);
  }
 else {
    labelModels.remove(labelModel);
  }
  adapter.notifyDataSetChanged();
}
