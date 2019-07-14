/** 
 * ??????
 * @param table
 * @param key
 * @param value
 * @param exceptId ???id
 * @throws Exception
 */
public static void verifyRepeat(String table,String key,Object value,long exceptId,@NotNull SQLCreator creator) throws Exception {
  if (key == null || value == null) {
    Log.e(TAG,"verifyRepeat  key == null || value == null >> return;");
    return;
  }
  if (value instanceof JSON) {
    throw new UnsupportedDataTypeException(key + ":value ?value??????JSON?");
  }
  SQLConfig config=creator.createSQLConfig().setMethod(RequestMethod.HEAD).setCount(1).setPage(0);
  config.setTable(table);
  if (exceptId > 0) {
    config.putWhere(JSONRequest.KEY_ID + "!",exceptId,false);
  }
  config.putWhere(key,value,false);
  SQLExecutor executor=creator.createSQLExecutor();
  try {
    JSONObject result=executor.execute(config,false);
    if (result == null) {
      throw new Exception("???????  verifyRepeat  result == null");
    }
    if (result.getIntValue(JSONResponse.KEY_COUNT) > 0) {
      throw new ConflictException(key + ": " + value + " ??????????");
    }
  }
  finally {
    executor.close();
  }
}
