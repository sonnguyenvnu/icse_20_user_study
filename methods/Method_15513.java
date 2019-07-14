/** 
 * ??????????????parentObject?
 * @param parentPath parentObject???
 * @param name parentObject?key
 * @param request parentObject?value
 * @return 
 * @throws Exception
 */
@Override public JSONArray onArrayParse(JSONObject request,String parentPath,String name,boolean isSubquery) throws Exception {
  if (Log.DEBUG) {
    Log.i(TAG,"\n\n\n onArrayParse parentPath = " + parentPath + "; name = " + name + "; request = " + JSON.toJSONString(request));
  }
  if (RequestMethod.isGetMethod(requestMethod,false) == false) {
    throw new UnsupportedOperationException("key[]:{}???GET??????? " + name + ":{} ?");
  }
  if (request == null || request.isEmpty()) {
    return null;
  }
  String path=getAbsPath(parentPath,name);
  final String query=request.getString(JSONRequest.KEY_QUERY);
  final Integer count=request.getInteger(JSONRequest.KEY_COUNT);
  final int page=request.getIntValue(JSONRequest.KEY_PAGE);
  final Object join=request.get(JSONRequest.KEY_JOIN);
  int query2;
  if (query == null) {
    query2=JSONRequest.QUERY_TABLE;
  }
 else {
switch (query) {
case "0":
case JSONRequest.QUERY_TABLE_STRING:
      query2=JSONRequest.QUERY_TABLE;
    break;
case "1":
case JSONRequest.QUERY_TOTAL_STRING:
  query2=JSONRequest.QUERY_TOTAL;
break;
case "2":
case JSONRequest.QUERY_ALL_STRING:
query2=JSONRequest.QUERY_ALL;
break;
default :
throw new IllegalArgumentException(path + "/" + JSONRequest.KEY_QUERY + ":value ? value ????????? [0,1,2] ? [TABLE, TOTAL, ALL] ? !");
}
}
int maxPage=getMaxQueryPage();
if (page < 0 || page > maxPage) {
throw new IllegalArgumentException(path + "/" + JSONRequest.KEY_PAGE + ":value ? value ????????? 0-" + maxPage + " ? !");
}
int count2=isSubquery || count != null ? (count == null ? 0 : count) : getDefaultQueryCount();
int max=isSubquery ? count2 : getMaxQueryCount();
if (count2 < 0 || count2 > max) {
throw new IllegalArgumentException(path + "/" + JSONRequest.KEY_COUNT + ":value ? value ????????? 0-" + max + " ? !");
}
request.remove(JSONRequest.KEY_QUERY);
request.remove(JSONRequest.KEY_COUNT);
request.remove(JSONRequest.KEY_PAGE);
request.remove(JSONRequest.KEY_JOIN);
Log.d(TAG,"onArrayParse  query = " + query + "; count = " + count + "; page = " + page + "; join = " + join);
if (request.isEmpty()) {
Log.e(TAG,"onArrayParse  request.isEmpty() >> return null;");
return null;
}
int size=count2 == 0 ? max : count2;
Log.d(TAG,"onArrayParse  size = " + size + "; page = " + page);
int index=name == null ? -1 : name.lastIndexOf("[]");
String childPath=index <= 0 ? null : Pair.parseEntry(name.substring(0,index),true).getKey();
String[] childKeys=StringUtil.split(childPath,"-",false);
if (childKeys == null || childKeys.length <= 0 || request.containsKey(childKeys[0]) == false) {
childKeys=null;
}
JSONArray response=new JSONArray();
SQLConfig config=createSQLConfig().setMethod(requestMethod).setCount(size).setPage(page).setQuery(query2).setJoinList(onJoinParse(join,request));
JSONObject parent;
for (int i=0; i < (isSubquery ? 1 : size); i++) {
parent=onObjectParse(request,path,"" + i,config.setType(SQLConfig.TYPE_ITEM).setPosition(i),isSubquery);
if (parent == null || parent.isEmpty()) {
break;
}
response.add(getValue(parent,childKeys));
}
Object fo=childKeys == null || response.isEmpty() ? null : response.get(0);
if (fo instanceof Boolean || fo instanceof Number || fo instanceof String) {
putQueryResult(path,response);
}
request.put(JSONRequest.KEY_QUERY,query);
request.put(JSONRequest.KEY_COUNT,count);
request.put(JSONRequest.KEY_PAGE,page);
request.put(JSONRequest.KEY_JOIN,join);
if (Log.DEBUG) {
Log.i(TAG,"onArrayParse  return response = \n" + JSON.toJSONString(response) + "\n>>>>>>>>>>>>>>>\n\n\n");
}
return response;
}
