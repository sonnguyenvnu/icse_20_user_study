/** 
 * Removes a breezemoon.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/remove-breezemoon",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After(StopwatchEndAdvice.class) public void removeBreezemoon(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final String id=context.param(Common.ID);
  try {
    breezemoonMgmtService.removeBreezemoon(id);
    operationMgmtService.addOperation(Operation.newOperation(request,Operation.OPERATION_CODE_C_REMOVE_BREEZEMOON,id));
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Removes a breezemoon failed",e);
  }
  context.sendRedirect(Latkes.getServePath() + "/admin/breezemoons");
}
