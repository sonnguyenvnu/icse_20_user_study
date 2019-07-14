@Override public boolean close(){
  if (!super.close()) {
    return false;
  }
  for (  DataSource<?> dataSource : mDataSources) {
    dataSource.close();
  }
  return true;
}
