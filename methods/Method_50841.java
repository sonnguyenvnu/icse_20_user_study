@Override public void analysisFailed(final File sourceFile){
  updatedResultsCache.remove(sourceFile.getPath());
}
