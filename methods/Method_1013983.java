@Override public void added(Provider<E> provider,E element){
  elementWriteLock.lock();
  try {
    final Collection<E> providerElements=providerToElements.get(provider);
    if (providerElements == null) {
      logger.warn("Cannot add \"{}\" with key \"{}\". Provider \"{}\" unknown.",element.getClass().getSimpleName(),element.getUID(),provider.getClass().getSimpleName());
      return;
    }
    if (!added(provider,element,providerElements)) {
      return;
    }
  }
  finally {
    elementWriteLock.unlock();
  }
  notifyListenersAboutAddedElement(element);
}
