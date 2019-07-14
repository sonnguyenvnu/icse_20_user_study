/** 
 * ????????????parentObject?
 * @param parentPath parentObject???
 * @param name parentObject?key
 * @param request parentObject?value
 * @param config for array item
 * @return
 * @throws Exception 
 */
@Override public JSONObject onObjectParse(final JSONObject request,String parentPath,String name,final SQLConfig arrayConfig,boolean isSubquery) throws Exception {
  if (Log.DEBUG) {
    Log.i(TAG,"\ngetObject:  parentPath = " + parentPath + ";\n name = " + name + "; request = " + JSON.toJSONString(request));
  }
  if (request == null) {
    return null;
  }
  int type=arrayConfig == null ? 0 : arrayConfig.getType();
  String[] arr=StringUtil.split(parentPath,"/");
  if (arrayConfig == null || arrayConfig.getPosition() == 0) {
    int d=arr == null ? 1 : arr.length + 1;
    if (queryDepth < d) {
      queryDepth=d;
      int maxQueryDepth=getMaxQueryDepth();
      if (queryDepth > maxQueryDepth) {
        throw new IllegalArgumentException(parentPath + "/" + name + ":{} ???(?????) ? " + queryDepth + " ??????? 1-" + maxQueryDepth + " ? !");
      }
    }
  }
  ObjectParser op=createObjectParser(request,parentPath,name,arrayConfig,isSubquery).parse();
  JSONObject response=null;
  if (op != null) {
    if (arrayConfig == null) {
      response=op.setSQLConfig().executeSQL().response();
    }
 else {
      int query=arrayConfig.getQuery();
      if (type == SQLConfig.TYPE_ITEM_CHILD_0 && query != JSONRequest.QUERY_TABLE && arrayConfig.getPosition() == 0) {
        JSONObject rp=op.setMethod(RequestMethod.HEAD).setSQLConfig().executeSQL().getSqlReponse();
        if (rp != null) {
          int index=parentPath.lastIndexOf("]/");
          if (index >= 0) {
            int total=rp.getIntValue(JSONResponse.KEY_COUNT);
            putQueryResult(parentPath.substring(0,index) + "]/" + JSONResponse.KEY_TOTAL,total);
            if (total <= arrayConfig.getCount() * arrayConfig.getPage()) {
              query=JSONRequest.QUERY_TOTAL;
            }
          }
        }
        op.setMethod(requestMethod);
      }
      if (query == JSONRequest.QUERY_TOTAL) {
        response=null;
      }
 else {
        response=op.setSQLConfig(arrayConfig.getCount(),arrayConfig.getPage(),arrayConfig.getPosition()).executeSQL().response();
      }
    }
    op.recycle();
    op=null;
  }
  return response;
}
