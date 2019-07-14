private void onDataSourceProgress(){
  float progress=0;
  for (  DataSource<?> dataSource : mDataSources) {
    progress+=dataSource.getProgress();
  }
  setProgress(progress / mDataSources.length);
}
