protected void fail(Throwable ex){
  future().setFailure(ExceptionUtils.getRootCause(ex));
}
