/** 
 * SQL???for array item
 * @param count
 * @param page
 * @param position
 * @return this
 * @throws Exception
 */
@Override public AbstractObjectParser executeSQL() throws Exception {
  if (isTable == false) {
    sqlReponse=new JSONObject(sqlRequest);
  }
 else {
    try {
      sqlReponse=onSQLExecute();
    }
 catch (    NotExistException e) {
      sqlReponse=null;
    }
    if (drop) {
      sqlReponse=null;
    }
  }
  return this;
}
