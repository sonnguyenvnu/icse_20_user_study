/** 
 * Constructs a new connection pool for H2 databases.
 * @param url the database URL of the H2 connection
 * @param user the user name
 * @param password the password
 * @return the connection pool
 */
public static JdbcConnectionPool create(String url,String user,String password){
  JdbcDataSource ds=new JdbcDataSource();
  ds.setURL(url);
  ds.setUser(user);
  ds.setPassword(password);
  return new JdbcConnectionPool(ds);
}
