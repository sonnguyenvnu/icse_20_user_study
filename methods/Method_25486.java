/** 
 * Returns if this checker should be suppressed on the current tree path.
 * @param suppressible holds information about the suppressibility of a checker
 * @param errorProneOptions Options object configuring whether or not to suppress non-errors ingenerated code.
 */
protected SuppressedState isSuppressed(Suppressible suppressible,ErrorProneOptions errorProneOptions){
  boolean suppressedInGeneratedCode=errorProneOptions.disableWarningsInGeneratedCode() && severityMap().get(suppressible.canonicalName()) != SeverityLevel.ERROR;
  return currentSuppressions.suppressedState(suppressible,suppressedInGeneratedCode);
}
