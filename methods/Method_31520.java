@Override protected SybaseASEConnection doGetConnection(Connection connection){
  return new SybaseASEConnection(this,connection);
}
