@SuppressWarnings("unchecked") public static List<Container.Entry> findInternalTypeName(Collection<Future<Indexes>> collectionOfFutureIndexes,String internalTypeName){
  return find(collectionOfFutureIndexes,"typeDeclarations",internalTypeName);
}
