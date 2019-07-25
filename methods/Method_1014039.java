@Override public void removed(Item item){
  if (item instanceof GenericItem) {
    GenericItem genericItem=(GenericItem)item;
    genericItem.removeStateChangeListener(this);
  }
}
