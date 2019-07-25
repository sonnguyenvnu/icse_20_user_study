@Override public void removed(Provider<E> provider,E element){
  final E existingElement;
  elementWriteLock.lock();
  try {
    final K uid=element.getUID();
    existingElement=identifierToElement.get(uid);
    if (existingElement == null) {
      logger.debug("Cannot remove \"{}\" with key \"{}\" from provider \"{}\" because it does not exist!",element.getClass().getSimpleName(),uid,provider.getClass().getSimpleName());
      return;
    }
    try {
      onRemoveElement(existingElement);
    }
 catch (    final RuntimeException ex) {
      logger.warn("Cannot remove \"{}\" with key \"{}\": {}",element.getClass().getSimpleName(),uid,ex.getMessage(),ex);
      return;
    }
    identifierToElement.remove(uid);
    elementToProvider.remove(existingElement);
    providerToElements.get(provider).remove(existingElement);
    elements.remove(existingElement);
  }
  finally {
    elementWriteLock.unlock();
  }
  notifyListenersAboutRemovedElement(existingElement);
}
