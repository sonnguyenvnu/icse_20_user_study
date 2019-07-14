@Override @SuppressWarnings("unchecked") public <T extends Extension>Map<String,T> extensionsOfType(final Class<T> extensionType){
  return (Map<String,T>)Maps.filterEntries(extensions,valueAssignableFrom(extensionType));
}
