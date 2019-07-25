protected void remove(String clustername,Address addr){
  final String addressAsString=addressAsString(addr);
  try {
    delete(clustername,addressAsString);
  }
 catch (  SQLException e) {
    log.error("Error",e);
  }
}
