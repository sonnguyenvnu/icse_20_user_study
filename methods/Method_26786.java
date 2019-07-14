@GetMapping("/deferred-result/exception") public @ResponseBody DeferredResult<String> deferredResultWithException(){
  DeferredResult<String> result=new DeferredResult<>();
  this.exceptionQueue.add(result);
  return result;
}
