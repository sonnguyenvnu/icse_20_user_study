/** 
 * Sets the location of the analysis cache to be used. This will automatically configure and appropriate AnalysisCache implementation.
 * @param cacheLocation The location of the analysis cache to be used.
 */
public void setAnalysisCacheLocation(final String cacheLocation){
  setAnalysisCache(cacheLocation == null ? new NoopAnalysisCache() : new FileAnalysisCache(new File(cacheLocation)));
}
