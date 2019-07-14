@Override public JSONObject onSQLExecute() throws Exception {
  JSONObject result=parser.executeSQL(sqlConfig,isSubquery);
  if (isSubquery == false && result != null) {
    parser.putQueryResult(path,result);
  }
  return result;
}
