/** 
 * Checks if a violation already exists. This is needed because on the different paths same anomalies can occur.
 * @param type
 * @param var
 * @param startLine
 * @param endLine
 * @return true if the violation already was added to the report
 */
private boolean violationAlreadyExists(String type,String var,int startLine,int endLine){
  for (  DaaRuleViolation violation : daaRuleViolations) {
    if (violation.getBeginLine() == startLine && violation.getEndLine() == endLine && violation.getType().equals(type) && violation.getVariableName().equals(var)) {
      return true;
    }
  }
  return false;
}
