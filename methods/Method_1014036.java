protected void activate(){
  allItemsChanged(null);
  started=true;
  itemRegistry.addRegistryChangeListener(this);
}
