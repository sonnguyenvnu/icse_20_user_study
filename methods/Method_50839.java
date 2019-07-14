@Override public boolean isUpToDate(final File sourceFile){
  final AnalysisResult updatedResult=new AnalysisResult(sourceFile);
  updatedResultsCache.put(sourceFile.getPath(),updatedResult);
  final AnalysisResult analysisResult=fileResultsCache.get(sourceFile.getPath());
  final boolean result=analysisResult != null && analysisResult.getFileChecksum() == updatedResult.getFileChecksum();
  if (LOG.isLoggable(Level.FINE)) {
    if (result) {
      LOG.fine("Incremental Analysis cache HIT");
    }
 else {
      LOG.fine("Incremental Analysis cache MISS - " + (analysisResult != null ? "file changed" : "no previous result found"));
    }
  }
  return result;
}
