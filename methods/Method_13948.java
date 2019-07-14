/** 
 * Helper to be used by subclasses to emit simple critical warnings
 * @param warning
 */
protected void critical(String type){
  addIssue(type,null,QAWarning.Severity.CRITICAL,1);
}
