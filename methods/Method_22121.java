@Override public void update(final String key,final String value){
  try {
    client.inTransaction().check().forPath(key).and().setData().forPath(key,value.getBytes(Charsets.UTF_8)).and().commit();
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
  }
}
