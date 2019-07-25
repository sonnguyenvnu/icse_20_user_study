@Override public void add(E element){
  String keyAsString=getKeyAsString(element);
  if (storage.get(keyAsString) != null) {
    throw new IllegalArgumentException("Cannot add element, because an element with same UID (" + keyAsString + ") already exists.");
  }
  storage.put(keyAsString,toPersistableElement(element));
  notifyListenersAboutAddedElement(element);
  logger.debug("Added new element {} to {}.",keyAsString,this.getClass().getSimpleName());
}
