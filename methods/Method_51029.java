@Override public final <T>boolean hasDescendantOfType(final Class<T> type){
  return getFirstDescendantOfType(type) != null;
}
