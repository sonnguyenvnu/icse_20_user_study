/** 
 * Registers new action result instance. If action result of the same class is already registered, registration will be skipped. If result for the same result type or same target class exist, it will be replaced! However, default Jodd results will <i>never</i> replace other results. After the registration, results are initialized.
 */
protected ActionResult register(final ActionResult result){
  Class<? extends ActionResult> actionResultClass=result.getClass();
  ActionResult existingResult=allResults.get(actionResultClass);
  if (existingResult != null) {
    if (log.isDebugEnabled()) {
      log.debug("ActionResult already registered: " + actionResultClass);
    }
    return existingResult;
  }
  allResults.put(actionResultClass,result);
  initializeResult(result);
  return result;
}
