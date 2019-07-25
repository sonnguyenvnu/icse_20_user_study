/** 
 * Creates an unpooled DataSource that users <TT>java.sql.DriverManager</TT> behind the scenes to acquire Connections.
 * @param driverClass a jdbc driver class that can resolve <TT>jdbcUrl</TT>.
 * @param jdbcUrl the jdbcUrl of the RDBMS that Connections should be made to.
 * @param dfltUser a username (may be null) for authentication to the RDBMS
 * @param dfltPassword a password (may be null) for authentication to the RDBMS
 * @param refFactoryLoc a codebase url where JNDI clients can find the  c3p0 libraries. Use null if clients will be expected to have the libraries available locally.
 */
public static DataSource create(String driverClass,String jdbcUrl,String dfltUser,String dfltPassword,String refFactoryLoc) throws SQLException {
  DriverManagerDataSource out=new DriverManagerDataSource();
  out.setDriverClass(driverClass);
  out.setJdbcUrl(jdbcUrl);
  out.setUser(dfltUser);
  out.setPassword(dfltPassword);
  out.setFactoryClassLocation(refFactoryLoc);
  return out;
}
