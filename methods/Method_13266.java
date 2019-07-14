public static boolean contains(Collection<Future<Indexes>> collectionOfFutureIndexes,String indexName,String key){
  try {
    for (    Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
      if (futureIndexes.isDone()) {
        Map<String,Collection> index=futureIndexes.get().getIndex(indexName);
        if ((index != null) && (index.get(key) != null)) {
          return true;
        }
      }
    }
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  return false;
}
