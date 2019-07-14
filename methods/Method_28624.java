private void initializeClientFromURI(URI uri){
  if (!JedisURIHelper.isValid(uri)) {
    throw new InvalidURIException(String.format("Cannot open Redis connection due invalid URI. %s",uri.toString()));
  }
  client=new Client(uri.getHost(),uri.getPort());
  String password=JedisURIHelper.getPassword(uri);
  if (password != null) {
    client.auth(password);
    client.getStatusCodeReply();
  }
  int dbIndex=JedisURIHelper.getDBIndex(uri);
  if (dbIndex > 0) {
    client.select(dbIndex);
    client.getStatusCodeReply();
    client.setDb(dbIndex);
  }
}
