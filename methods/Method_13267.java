@SuppressWarnings("unchecked") public static List<Container.Entry> find(Collection<Future<Indexes>> collectionOfFutureIndexes,String indexName,String key){
  ArrayList<Container.Entry> entries=new ArrayList<>();
  try {
    for (    Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
      if (futureIndexes.isDone()) {
        Map<String,Collection> index=futureIndexes.get().getIndex(indexName);
        if (index != null) {
          Collection<Container.Entry> collection=index.get(key);
          if (collection != null) {
            entries.addAll(collection);
          }
        }
      }
    }
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  return entries;
}
