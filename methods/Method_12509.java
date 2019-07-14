protected Mono<StatusInfo> handleError(Throwable ex){
  Map<String,Object> details=new HashMap<>();
  details.put("message",ex.getMessage());
  details.put("exception",ex.getClass().getName());
  return Mono.just(StatusInfo.ofOffline(details));
}
