@Override public void close(){
  try {
    httpResponse.getEntity().consumeContent();
  }
 catch (  IOException e) {
    ;
  }
}
