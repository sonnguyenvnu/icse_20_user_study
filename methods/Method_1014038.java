@Override public void added(Item item){
  initialize(item);
  if (item instanceof GenericItem) {
    GenericItem genericItem=(GenericItem)item;
    genericItem.addStateChangeListener(this);
  }
}
