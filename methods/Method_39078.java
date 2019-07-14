/** 
 * Creates new  {@link jodd.madvoc.result.ActionResult}.
 */
protected ActionResult createResult(final Class<? extends ActionResult> actionResultClass){
  try {
    return ClassUtil.newInstance(actionResultClass);
  }
 catch (  Exception ex) {
    throw new MadvocException("Invalid Madvoc result: " + actionResultClass,ex);
  }
}
