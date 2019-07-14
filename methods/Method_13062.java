@SuppressWarnings("unchecked") protected static String getOuterPath(Collection<Future<Indexes>> collectionOfFutureIndexes,Container.Entry entry,Type type){
  String outerName=type.getOuterName();
  if (outerName != null) {
    try {
      for (      Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
        if (futureIndexes.isDone()) {
          Collection<Container.Entry> outerEntries=futureIndexes.get().getIndex("typeDeclarations").get(outerName);
          if (outerEntries != null) {
            for (            Container.Entry outerEntry : outerEntries) {
              if (outerEntry.getContainer() == entry.getContainer()) {
                return outerEntry.getUri().getPath();
              }
            }
          }
        }
      }
    }
 catch (    Exception e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
  return entry.getUri().getPath();
}
