@Override protected void updateItem(T item,boolean empty){
  super.updateItem(item,empty);
  updateDisplay(item,empty);
  setMouseTransparent(item == null || empty);
}
