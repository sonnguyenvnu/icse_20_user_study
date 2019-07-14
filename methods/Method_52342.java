/** 
 * Maximum number of violations was already reached?
 * @return <code>true</code> if the maximum number of violations wasreached, <code>false</code> otherwise.
 */
private boolean maxNumberOfViolationsReached(){
  return currentRuleViolationCount >= maxRuleViolations;
}
