protected boolean shouldNotBeWrapped(Throwable underlying){
  return underlying instanceof ExceptionNotWrappedByHystrix;
}
