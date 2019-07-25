/** 
 * Execute the statement.
 * @return the update count
 * @throws DbException if it is a query
 */
public int update(){
  throw DbException.get(ErrorCode.METHOD_NOT_ALLOWED_FOR_QUERY);
}
