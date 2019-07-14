@Override public void onFailure(DataSource<T> dataSource){
  try {
    onFailureImpl(dataSource);
  }
  finally {
    dataSource.close();
  }
}
