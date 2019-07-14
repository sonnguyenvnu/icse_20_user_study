@Override public List<RuleViolation> getCachedViolations(final File sourceFile){
  final AnalysisResult analysisResult=fileResultsCache.get(sourceFile.getPath());
  if (analysisResult == null) {
    return Collections.emptyList();
  }
  return analysisResult.getViolations();
}
