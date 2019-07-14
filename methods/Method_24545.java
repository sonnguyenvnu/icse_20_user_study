protected void nope(String function){
  throw new RuntimeException("No " + function + "() for " + getClass().getSimpleName());
}
