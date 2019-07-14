@Override protected DerbyConnection doGetConnection(Connection connection){
  return new DerbyConnection(this,connection);
}
