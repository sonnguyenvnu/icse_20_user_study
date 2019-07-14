/** 
 * ???? response????
 * @return null or this
 * @throws Exception
 */
@Override public AbstractObjectParser parse() throws Exception {
  if (isInvalidate() == false) {
    breakParse=false;
    response=new JSONObject(true);
    sqlRequest=new JSONObject(true);
    sqlReponse=null;
    customMap=null;
    functionMap=null;
    childMap=null;
    Set<Entry<String,Object>> set=new LinkedHashSet<Entry<String,Object>>(request.entrySet());
    if (set != null && set.isEmpty() == false) {
      if (isTable) {
        customMap=new LinkedHashMap<String,Object>();
        childMap=new LinkedHashMap<String,JSONObject>();
      }
      functionMap=new LinkedHashMap<String,Map<String,String>>();
      List<String> whereList=null;
      if (method == PUT) {
        String[] combine=StringUtil.split(request.getString(KEY_COMBINE));
        if (combine != null) {
          String w;
          for (int i=0; i < combine.length; i++) {
            w=combine[i];
            if (w != null && (w.startsWith("&") || w.startsWith("|") || w.startsWith("!"))) {
              combine[i]=w.substring(1);
            }
          }
        }
        whereList=new ArrayList<String>(Arrays.asList(combine != null ? combine : new String[]{}));
        whereList.add(zuo.biao.apijson.JSONRequest.KEY_ID);
        whereList.add(zuo.biao.apijson.JSONRequest.KEY_ID_IN);
      }
      String key;
      Object value;
      int index=0;
      for (      Entry<String,Object> entry : set) {
        if (isBreakParse()) {
          break;
        }
        value=entry.getValue();
        if (value == null) {
          continue;
        }
        key=entry.getKey();
        try {
          if (value instanceof JSONObject && key.startsWith("@") == false && key.endsWith("@") == false) {
            if (childMap != null) {
              childMap.put(key,(JSONObject)value);
            }
 else {
              response.put(key,onChildParse(index,key,(JSONObject)value));
              index++;
            }
          }
 else           if (method == PUT && value instanceof JSONArray && (whereList == null || whereList.contains(key) == false)) {
            onPUTArrayParse(key,(JSONArray)value);
          }
 else {
            if (onParse(key,value) == false) {
              invalidate();
            }
          }
        }
 catch (        Exception e) {
          if (tri == false) {
            throw e;
          }
          invalidate();
        }
      }
    }
    if (isTable) {
      if (sqlRequest.get(JSONRequest.KEY_DATABASE) == null && parser.getGlobleDatabase() != null) {
        sqlRequest.put(JSONRequest.KEY_DATABASE,parser.getGlobleDatabase());
      }
      if (sqlRequest.get(JSONRequest.KEY_SCHEMA) == null && parser.getGlobleSchema() != null) {
        sqlRequest.put(JSONRequest.KEY_SCHEMA,parser.getGlobleSchema());
      }
      if (sqlRequest.get(JSONRequest.KEY_EXPLAIN) == null && parser.getGlobleExplain() != null) {
        sqlRequest.put(JSONRequest.KEY_EXPLAIN,parser.getGlobleExplain());
      }
      if (sqlRequest.get(JSONRequest.KEY_CACHE) == null && parser.getGlobleCache() != null) {
        sqlRequest.put(JSONRequest.KEY_CACHE,parser.getGlobleCache());
      }
    }
  }
  if (isInvalidate()) {
    recycle();
    return null;
  }
  return this;
}
