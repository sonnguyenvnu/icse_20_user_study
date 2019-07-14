@Override public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  page.indexesChanged(lastCollectionOfFutureIndexes=collectionOfFutureIndexes);
}
