/** 
 * Handle an element that has been added for a provider. <p> This method must only be called if the write lock for elements has been locked!
 * @param provider the provider that provides the element
 * @param element the element that has been added
 * @param providerElements the collection that holds the elements of the provider
 * @return indication if the element has been added
 */
private boolean added(Provider<E> provider,E element,Collection<E> providerElements){
  final K uid=element.getUID();
  if (identifierToElement.containsKey(uid)) {
    logger.warn("Cannot add \"{}\" with key \"{}\". It exists already from provider \"{}\"! Failed to add a second with the same UID from provider \"{}\"!",element.getClass().getSimpleName(),uid,elementToProvider.get(identifierToElement.get(uid)).getClass().getSimpleName(),provider.getClass().getSimpleName());
    return false;
  }
  try {
    onAddElement(element);
  }
 catch (  final RuntimeException ex) {
    logger.warn("Cannot add \"{}\" with key \"{}\": {}",element.getClass().getSimpleName(),uid,ex.getMessage(),ex);
    return false;
  }
  identifierToElement.put(element.getUID(),element);
  elementToProvider.put(element,provider);
  providerElements.add(element);
  elements.add(element);
  return true;
}
