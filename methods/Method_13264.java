public static boolean containsInternalTypeName(Collection<Future<Indexes>> collectionOfFutureIndexes,String internalTypeName){
  return contains(collectionOfFutureIndexes,"typeDeclarations",internalTypeName);
}
