private int unimp(){
  IllegalStateException ex=new IllegalStateException("Unimlemented!");
  LOG.log(Level.WARNING,ex.getMessage(),ex);
  throw ex;
}
