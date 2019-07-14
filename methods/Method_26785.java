@GetMapping("/custom-timeout-handling") public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling(){
  Callable<String> callable=() -> {
    Thread.sleep(2000);
    return "Callable result";
  }
;
  return new WebAsyncTask<String>(1000,callable);
}
