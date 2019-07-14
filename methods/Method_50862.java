/** 
 * Converts these parameters into a configuration.
 * @return A new PMDConfiguration corresponding to these parameters
 * @throws IllegalArgumentException if the parameters are inconsistent or incomplete
 */
public PMDConfiguration toConfiguration(){
  if (null == this.getSourceDir() && null == this.getUri() && null == this.getFileListPath()) {
    throw new IllegalArgumentException("Please provide a parameter for source root directory (-dir or -d), database URI (-uri or -u), or file list path (-filelist).");
  }
  PMDConfiguration configuration=new PMDConfiguration();
  configuration.setInputPaths(this.getSourceDir());
  configuration.setInputFilePath(this.getFileListPath());
  configuration.setIgnoreFilePath(this.getIgnoreListPath());
  configuration.setInputUri(this.getUri());
  configuration.setReportFormat(this.getFormat());
  configuration.setBenchmark(this.isBenchmark());
  configuration.setDebug(this.isDebug());
  configuration.setMinimumPriority(this.getMinimumPriority());
  configuration.setReportFile(this.getReportfile());
  configuration.setReportProperties(this.getProperties());
  configuration.setReportShortNames(this.isShortnames());
  configuration.setRuleSets(this.getRulesets());
  configuration.setRuleSetFactoryCompatibilityEnabled(!this.noRuleSetCompatibility);
  configuration.setShowSuppressedViolations(this.isShowsuppressed());
  configuration.setSourceEncoding(this.getEncoding());
  configuration.setStressTest(this.isStress());
  configuration.setSuppressMarker(this.getSuppressmarker());
  configuration.setThreads(this.getThreads());
  configuration.setFailOnViolation(this.isFailOnViolation());
  configuration.setAnalysisCacheLocation(this.cacheLocation);
  configuration.setIgnoreIncrementalAnalysis(this.isIgnoreIncrementalAnalysis());
  LanguageVersion languageVersion=LanguageRegistry.findLanguageVersionByTerseName(this.getLanguage() + ' ' + this.getVersion());
  if (languageVersion != null) {
    configuration.getLanguageVersionDiscoverer().setDefaultLanguageVersion(languageVersion);
  }
  try {
    configuration.prependClasspath(this.getAuxclasspath());
  }
 catch (  IOException e) {
    throw new IllegalArgumentException("Invalid auxiliary classpath: " + e.getMessage(),e);
  }
  return configuration;
}
