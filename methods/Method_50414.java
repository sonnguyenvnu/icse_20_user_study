private static HmilyTransaction cacheHmilyTransaction(final String key){
  return Optional.ofNullable(coordinatorService.findByTransId(key)).orElse(new HmilyTransaction());
}
