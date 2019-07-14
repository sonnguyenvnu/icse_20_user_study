/** 
 * @return a BugCheckerInfo with the same information as this class, except that its defaultseverity is the passed in paramter. If this checker's current defaultSeverity is the same as the argument, return this.
 */
public BugCheckerInfo withCustomDefaultSeverity(SeverityLevel defaultSeverity){
  if (defaultSeverity == this.defaultSeverity) {
    return this;
  }
  return new BugCheckerInfo(checker,canonicalName,allNames,message,defaultSeverity,linkUrl,supportsSuppressWarnings,customSuppressionAnnotations,tags,disableable);
}
