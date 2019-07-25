public static RuntimeException propagate(Exception e){
  if (e instanceof RuntimeException) {
    throw (RuntimeException)e;
  }
  throw new RuntimeException(e);
}
