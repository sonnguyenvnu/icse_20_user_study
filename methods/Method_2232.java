@Override public void onNewResult(FrescoState frescoState,DataSource<CloseableReference<CloseableImage>> dataSource){
  if (dataSource != null && !dataSource.isClosed()) {
    final boolean shouldClose=mFrescoContext.getExperiments().closeDatasourceOnNewResult() && dataSource.isFinished();
    final CloseableReference<CloseableImage> result=dataSource.getResult();
    try {
      frescoState.setImageFetched(true);
      if (frescoState.isAttached()) {
        displayResultOrError(frescoState,result,false);
      }
    }
  finally {
      CloseableReference.closeSafely(result);
      if (shouldClose) {
        dataSource.close();
      }
    }
  }
}
