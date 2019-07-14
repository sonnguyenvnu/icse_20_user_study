private void addItemsToDeny(final String[] items){
  if (items == null) {
    return;
  }
  for (int i=0; i < items.length; ++i) {
    String item=items[i];
    this.addDeny(item);
  }
}
