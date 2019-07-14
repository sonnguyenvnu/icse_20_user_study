@GetMapping("/deferred-result/timeout-value") public @ResponseBody DeferredResult<String> deferredResultWithTimeoutValue(){
  return new DeferredResult<>(1000L,"Deferred result after timeout");
}
