/** 
 * @see java.sql.ResultSet#next()
 */
public boolean next() throws SQLException {
  if (!open) {
    return false;
  }
  lastCol=-1;
  if (row == 0) {
    row++;
    return true;
  }
  if (maxRows != 0 && row == maxRows) {
    return false;
  }
  int statusCode=getDatabase().step(stmt.pointer);
switch (statusCode) {
case SQLITE_DONE:
    close();
  return false;
case SQLITE_ROW:
row++;
return true;
case SQLITE_BUSY:
default :
getDatabase().throwex(statusCode);
return false;
}
}
