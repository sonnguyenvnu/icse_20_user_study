/** 
 * Lookups for  {@link jodd.madvoc.result.ActionResult action result handler}based on current  {@link jodd.madvoc.ActionRequest action request} and action methodresult object.
 */
public ActionResult lookup(final ActionRequest actionRequest,final Object resultObject){
  ActionResult actionResultHandler=null;
{
    final ActionRuntime actionRuntime=actionRequest.getActionRuntime();
    final Class<? extends ActionResult> actionResultClass=actionRuntime.getActionResult();
    if (actionResultClass != null) {
      actionResultHandler=lookupAndRegisterIfMissing(actionResultClass);
    }
  }
  if (actionResultHandler == null && resultObject != null) {
    final RenderWith renderWith=resultObject.getClass().getAnnotation(RenderWith.class);
    if (renderWith != null) {
      actionResultHandler=lookupAndRegisterIfMissing(renderWith.value());
    }
 else     if (resultObject instanceof ActionResult) {
      actionResultHandler=(ActionResult)resultObject;
    }
  }
  if (actionResultHandler == null) {
    final ActionRuntime actionRuntime=actionRequest.getActionRuntime();
    final Class<? extends ActionResult> actionResultClass=actionRuntime.getDefaultActionResult();
    if (actionResultClass != null) {
      actionResultHandler=lookupAndRegisterIfMissing(actionResultClass);
    }
  }
  if (actionResultHandler == null) {
    throw new MadvocException("ActionResult not found for: " + resultObject);
  }
  actionRequest.bindActionResult(resultObject);
  return actionResultHandler;
}
