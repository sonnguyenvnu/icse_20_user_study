private DataSource dereference() throws SQLException {
  Object jndiName=this.getJndiName();
  Hashtable jndiEnv=this.getJndiEnv();
  try {
    InitialContext ctx;
    if (jndiEnv != null)     ctx=new InitialContext(jndiEnv);
 else     ctx=new InitialContext();
    if (jndiName instanceof String)     return (DataSource)ctx.lookup((String)jndiName);
 else     if (jndiName instanceof Name)     return (DataSource)ctx.lookup((Name)jndiName);
 else     throw new SQLException("Could not find ConnectionPoolDataSource with " + "JNDI name: " + jndiName);
  }
 catch (  NamingException e) {
    if (logger.isLoggable(MLevel.WARNING))     logger.log(MLevel.WARNING,"An Exception occurred while trying to look up a target DataSource via JNDI!",e);
    throw SqlUtils.toSQLException(e);
  }
}
