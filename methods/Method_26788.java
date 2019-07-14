@Scheduled(fixedRate=2000) public void processQueues(){
  for (  DeferredResult<String> result : this.responseBodyQueue) {
    result.setResult("Deferred result");
    this.responseBodyQueue.remove(result);
  }
  for (  DeferredResult<String> result : this.exceptionQueue) {
    result.setErrorResult(new IllegalStateException("DeferredResult error"));
    this.exceptionQueue.remove(result);
  }
  for (  DeferredResult<ModelAndView> result : this.mavQueue) {
    result.setResult(new ModelAndView("views/html","javaBean",new JavaBean("bar","apple")));
    this.mavQueue.remove(result);
  }
}
