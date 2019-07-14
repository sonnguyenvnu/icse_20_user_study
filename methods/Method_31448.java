@Override protected RedshiftConnection doGetConnection(Connection connection){
  return new RedshiftConnection(this,connection);
}
