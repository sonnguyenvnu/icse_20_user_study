/** 
 * Invokes an action asynchronously by submitting it to the thread pool.
 */
public void invoke(final ActionRequest actionRequest){
  if (executorService == null) {
    throw new MadvocException("No action is marked as async!");
  }
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  log.debug(() -> "Async call to: " + actionRequest);
  final AsyncContext asyncContext=servletRequest.startAsync();
  executorService.submit(() -> {
    try {
      actionRequest.invoke();
    }
 catch (    Exception ex) {
      log.error("Invoking async action path failed: ",ExceptionUtil.unwrapThrowable(ex));
    }
 finally {
      asyncContext.complete();
    }
  }
);
}
