private <T>T _unsupported(){
  throw new UnsupportedOperationException("Operation should not be attempted on " + getClass().getName());
}
