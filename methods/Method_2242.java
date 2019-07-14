@Override public void onNewResult(DataSource<CloseableReference<CloseableImage>> dataSource){
  mFrescoContext.getController().onNewResult(this,dataSource);
}
