@Override public boolean accepts(DataSource ds){
  String connId=ds.getConnector().getId();
  return this.credentials.containsKey(connId);
}
