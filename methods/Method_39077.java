/** 
 * Initializes action result.
 */
protected void initializeResult(final ActionResult result){
  contextInjectorComponent.injectContext(result);
}
