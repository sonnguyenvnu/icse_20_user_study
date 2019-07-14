@Override public Object intercept(final ActionRequest actionRequest){
  try {
    return actionRequest.invoke();
  }
 catch (  ResponseException rex) {
    return JsonResult.of(HttpStatus.of(rex.getStatus(),rex.getMessage()));
  }
catch (  Exception ex) {
    log.error("Action execution failed:",ex);
    return JsonResult.of(ex);
  }
}
