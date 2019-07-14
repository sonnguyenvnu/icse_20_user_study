private boolean isDegradeFailure(BlockException ex){
  return ex instanceof DegradeException;
}
