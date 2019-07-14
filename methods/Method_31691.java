/** 
 * Returns the failed statement in SQL script.
 * @return The failed statement.
 */
public String getStatement(){
  return statement == null ? "" : statement.getSql();
}
