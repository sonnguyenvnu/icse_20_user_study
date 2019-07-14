/** 
 * validate param
 * @param in
 * @param eventListener
 */
private void validateParam(InputStream in,AnalysisEventListener eventListener){
  if (eventListener == null) {
    throw new IllegalArgumentException("AnalysisEventListener can not null");
  }
 else   if (in == null) {
    throw new IllegalArgumentException("InputStream can not null");
  }
}
