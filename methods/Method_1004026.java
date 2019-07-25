static <ContextT>LimiterRegistry<ContextT> single(Limiter<ContextT> limiter){
  return key -> limiter;
}
