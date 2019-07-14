@Override public void ruleViolationAdded(final RuleViolation ruleViolation){
  final AnalysisResult analysisResult=updatedResultsCache.get(ruleViolation.getFilename());
  analysisResult.addViolation(ruleViolation);
}
