@Override public void onToggleSelection(int position,boolean select){
  if (select) {
    getSelectionMap().put(position,adapter.getItem(position));
  }
 else {
    getSelectionMap().remove(position);
  }
  adapter.notifyDataSetChanged();
}
