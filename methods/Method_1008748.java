/** 
 * @see java.sql.Driver#connect(java.lang.String,java.util.Properties)
 */
public Connection connect(String url,Properties info) throws SQLException {
  return createConnection(url,info);
}
