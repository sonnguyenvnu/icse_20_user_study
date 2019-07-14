/** 
 * Helper to be used by subclasses to emit simple important warnings
 * @param warning
 */
protected void important(String type){
  addIssue(type,null,QAWarning.Severity.IMPORTANT,1);
}
