@Override public String explain(){
  try {
    SearchRequestBuilder source=deleteByQueryRequestBuilder.source();
    return source.toString();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
