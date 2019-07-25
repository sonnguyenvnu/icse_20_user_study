@Override public void updated(Provider<E> provider,E oldElement,E element){
  final K uidOld=oldElement.getUID();
  final K uid=element.getUID();
  if (!uidOld.equals(uid)) {
    logger.warn("Received update event for elements that UID differ (old: \"{}\", new: \"{}\"). Ignore event.",uidOld,uid);
    return;
  }
  final E existingElement;
  elementWriteLock.lock();
  try {
    existingElement=identifierToElement.get(uid);
    if (existingElement == null) {
      logger.debug("Cannot update \"{}\" with key \"{}\" for provider \"{}\" because it does not exist!",element.getClass().getSimpleName(),uid,provider.getClass().getSimpleName());
      return;
    }
    try {
      beforeUpdateElement(existingElement);
      onUpdateElement(oldElement,element);
    }
 catch (    final RuntimeException ex) {
      logger.warn("Cannot update \"{}\" with key \"{}\": {}",element.getClass().getSimpleName(),uid,ex.getMessage(),ex);
      return;
    }
    identifierToElement.put(uid,element);
    elementToProvider.remove(existingElement);
    elementToProvider.put(element,provider);
    final Collection<E> providerElements=providerToElements.get(provider);
    providerElements.remove(existingElement);
    providerElements.add(element);
    elements.remove(existingElement);
    elements.add(element);
  }
  finally {
    elementWriteLock.unlock();
  }
  notifyListenersAboutUpdatedElement(oldElement,element);
}
