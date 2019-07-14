@Override protected void updateItem(T item,boolean empty){
  super.updateItem(item,empty);
  if (isGroupItem(item)) {
    setDisclosureNode(null);
  }
}
