private static <T>Collection<T> collection2Collection(Collection src,Class<? extends Collection> clazz,Class<T> genericType){
  return arrayToCollection(src.toArray(),clazz,genericType);
}
