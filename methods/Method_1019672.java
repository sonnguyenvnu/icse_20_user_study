@Override protected void error(Request request,Response response,Exception cause){
  throw new RuntimeException(cause);
}
