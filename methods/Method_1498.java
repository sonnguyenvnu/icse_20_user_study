@Override @Nullable public synchronized List<CloseableReference<T>> getResult(){
  if (!hasResult()) {
    return null;
  }
  List<CloseableReference<T>> results=new ArrayList<>(mDataSources.length);
  for (  DataSource<CloseableReference<T>> dataSource : mDataSources) {
    results.add(dataSource.getResult());
  }
  return results;
}
