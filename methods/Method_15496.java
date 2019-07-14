public void parseFunction(JSONObject json,String key,String value) throws Exception {
  Object result;
  if (key.startsWith("@")) {
    FunctionBean fb=RemoteFunction.parseFunction(value,json,true);
    SQLConfig config=newSQLConfig(true);
    config.setProcedure(fb.toFunctionCallString(true));
    SQLExecutor executor=null;
    try {
      executor=parser.getSQLExecutor();
      result=executor.execute(config,true);
    }
 catch (    NotExistException e) {
      e.printStackTrace();
      return;
    }
  }
 else {
    result=parser.onFunctionParse(json,value);
  }
  if (result != null) {
    String k=AbstractSQLConfig.getRealKey(method,key,false,false,"`");
    response.put(k,result);
    parser.putQueryResult(AbstractParser.getAbsPath(path,k),result);
  }
}
