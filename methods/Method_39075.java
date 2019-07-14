/** 
 * Lookups for action result and  {@link #register(Class) registers} it if missing.
 */
private ActionResult lookupAndRegisterIfMissing(final Class<? extends ActionResult> actionResultClass){
  ActionResult actionResult=allResults.get(actionResultClass);
  if (actionResult == null) {
    actionResult=register(actionResultClass);
  }
  return actionResult;
}
