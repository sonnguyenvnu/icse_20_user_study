@Override protected ActionResult createResult(final Class<? extends ActionResult> actionResultClass){
  return petiteContainer.createBean(actionResultClass);
}
