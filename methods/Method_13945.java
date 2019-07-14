/** 
 * Emits an issue that will be reported to the user, after merging with other issues of the same kind.
 * @param warning the issue to report
 */
protected void addIssue(QAWarning warning){
  _store.addWarning(warning);
}
