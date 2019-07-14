/** 
 * Invokes a result after the action invocation. <p> Results may be objects that specify which action result will be used to render the result. <p> Result value may consist of two parts: type and value. Result type is optional and, if exists, it is separated by semi-colon from the value. If type is not specified then the default result type if still not defined. Result type defines which {@link ActionResult} should be used for rendering the value.<p> Result value is first checked against aliased values. Then, it is resolved and then passed to the founded  {@link ActionResult}.
 * @see ActionResult#render(jodd.madvoc.ActionRequest,Object)
 */
@SuppressWarnings("unchecked") public void render(final ActionRequest actionRequest,final Object resultObject) throws Exception {
  final ActionResult actionResult=resultsManager.lookup(actionRequest,resultObject);
  if (actionResult == null) {
    throw new MadvocException("Action result not found");
  }
  if (preventCaching) {
    ServletUtil.preventCaching(actionRequest.getHttpServletResponse());
  }
  log.debug(() -> "Result type: " + actionResult.getClass().getSimpleName());
  actionResult.render(actionRequest,actionRequest.getActionResult());
}
