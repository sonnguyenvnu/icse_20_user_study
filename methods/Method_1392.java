@Override public void onFailure(DataSource<Boolean> dataSource){
  try {
    onFailureImpl(dataSource);
  }
  finally {
    dataSource.close();
  }
}
