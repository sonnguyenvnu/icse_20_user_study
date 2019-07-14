/** 
 * @param method
 * @param config
 * @param joinList
 * @param callback
 * @return
 * @throws Exception
 */
public static AbstractSQLConfig parseJoin(RequestMethod method,AbstractSQLConfig config,List<Join> joinList,Callback callback) throws Exception {
  boolean isQuery=RequestMethod.isQueryMethod(method);
  config.setKeyPrefix(isQuery && config.isMain() == false);
  if (joinList == null || joinList.isEmpty() || RequestMethod.isQueryMethod(method) == false) {
    return config;
  }
  String name;
  for (  Join j : joinList) {
    name=j.getName();
    SQLConfig joinConfig=newSQLConfig(method,name,j.getTable(),null,false,callback);
    SQLConfig cacheConfig=newSQLConfig(method,name,j.getTable(),null,false,callback).setCount(1);
    if (j.isAppJoin() == false) {
      if (joinConfig.getDatabase() == null) {
        joinConfig.setDatabase(config.getDatabase());
      }
 else       if (joinConfig.getDatabase().equals(config.getDatabase()) == false) {
        throw new IllegalArgumentException("?? " + config.getTable() + " ? @database:" + config.getDatabase() + " ?? SQL JOIN ??? " + name + " ? @database:" + joinConfig.getDatabase() + " ????");
      }
      if (joinConfig.getSchema() == null) {
        joinConfig.setSchema(config.getSchema());
      }
      cacheConfig.setDatabase(joinConfig.getDatabase()).setSchema(joinConfig.getSchema());
      if (isQuery) {
        config.setKeyPrefix(true);
      }
      joinConfig.setMain(false).setKeyPrefix(true);
      if (j.isLeftOrRightJoin()) {
        SQLConfig outterConfig=newSQLConfig(method,name,j.getOutter(),null,false,callback);
        outterConfig.setMain(false).setKeyPrefix(true).setDatabase(joinConfig.getDatabase()).setSchema(joinConfig.getSchema());
        j.setOutterConfig(outterConfig);
      }
    }
    if (RequestMethod.isHeadMethod(method,true)) {
      joinConfig.setMethod(GET);
      joinConfig.setColumn(Arrays.asList(j.getKey()));
      cacheConfig.setMethod(GET);
      cacheConfig.setColumn(Arrays.asList(j.getKey()));
    }
    j.setJoinConfig(joinConfig);
    j.setCacheConfig(cacheConfig);
  }
  config.setJoinList(joinList);
  return config;
}
