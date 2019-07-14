/** 
 * Helper to be used by subclasses to emit simple INFO warnings
 * @param warning
 */
protected void info(String type){
  addIssue(type,null,QAWarning.Severity.INFO,1);
}
