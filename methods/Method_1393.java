@Override public void onNewResult(DataSource<T> dataSource){
  final boolean shouldClose=dataSource.isFinished();
  try {
    onNewResultImpl(dataSource);
  }
  finally {
    if (shouldClose) {
      dataSource.close();
    }
  }
}
