/** 
 * Determines whether the specified request is sending with pjax.
 * @param context the specified request context
 * @return {@code true} if it is sending with pjax, otherwise returns {@code false}
 */
private static boolean isPJAX(final RequestContext context){
  final boolean pjax=Boolean.valueOf(context.header("X-PJAX"));
  final String pjaxContainer=context.header("X-PJAX-Container");
  return pjax && StringUtils.isNotBlank(pjaxContainer);
}
