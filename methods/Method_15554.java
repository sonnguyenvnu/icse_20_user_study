/** 
 * ??SQL??
 * @param table
 * @param request
 * @param joinList
 * @param isProcedure
 * @param callback
 * @return
 * @throws Exception 
 */
public static AbstractSQLConfig newSQLConfig(RequestMethod method,String table,JSONObject request,List<Join> joinList,boolean isProcedure,Callback callback) throws Exception {
  if (request == null) {
    throw new NullPointerException(TAG + ": newSQLConfig  request == null!");
  }
  AbstractSQLConfig config=callback.getSQLConfig(method,table);
  String database=request.getString(KEY_DATABASE);
  String schema=request.getString(KEY_SCHEMA);
  config.setDatabase(database);
  config.setSchema(schema);
  if (isProcedure == false) {
    config=parseJoin(method,config,joinList,callback);
  }
  if (request.isEmpty()) {
    return config;
  }
  if (isProcedure) {
    return config;
  }
  String idKey=callback.getIdKey(schema,table);
  String idInKey=idKey + "{}";
  String userIdKey=callback.getUserIdKey(schema,table);
  String userIdInKey=userIdKey + "{}";
  Object idIn=request.get(idInKey);
  if (method == POST) {
    if (idIn != null) {
      if ((idIn instanceof List == false) || ((List<?>)idIn).isEmpty()) {
        throw new IllegalArgumentException("POST??????????? id{}:[] ? [] ???JSONArray??????");
      }
    }
 else     if (request.get(idKey) == null) {
      request.put(idKey,callback.newId(method,table));
    }
  }
  Object id=request.get(idKey);
  if (id != null) {
    if (id instanceof Number) {
      if (((Number)id).longValue() <= 0) {
        throw new NotExistException(TAG + ": newSQLConfig " + table + ".id <= 0");
      }
    }
 else     if (id instanceof String) {
      if (StringUtil.isEmpty(id,true)) {
        throw new NotExistException(TAG + ": newSQLConfig StringUtil.isEmpty(" + table + ".id, true)");
      }
    }
 else     if (id instanceof Subquery) {
    }
 else {
      throw new IllegalArgumentException(idKey + ":value ? value ?????? Long , String ? Subquery ?");
    }
    if (idIn instanceof List) {
      boolean contains=false;
      List<?> ids=((List<?>)idIn);
      Object d;
      for (int i=0; i < ids.size(); i++) {
        d=ids.get(i);
        if (d != null && id.toString().equals(d.toString())) {
          contains=true;
          break;
        }
      }
      if (contains == false) {
        throw new NotExistException(TAG + ": newSQLConfig  idIn != null && (((List<?>) idIn).contains(id) == false");
      }
    }
  }
  String role=request.getString(KEY_ROLE);
  boolean explain=request.getBooleanValue(KEY_EXPLAIN);
  String cache=request.getString(KEY_CACHE);
  String combine=request.getString(KEY_COMBINE);
  Subquery from=(Subquery)request.get(KEY_FROM);
  String column=request.getString(KEY_COLUMN);
  String group=request.getString(KEY_GROUP);
  String having=request.getString(KEY_HAVING);
  String order=request.getString(KEY_ORDER);
  request.remove(idKey);
  request.remove(idInKey);
  request.remove(KEY_ROLE);
  request.remove(KEY_EXPLAIN);
  request.remove(KEY_CACHE);
  request.remove(KEY_DATABASE);
  request.remove(KEY_SCHEMA);
  request.remove(KEY_COMBINE);
  request.remove(KEY_FROM);
  request.remove(KEY_COLUMN);
  request.remove(KEY_GROUP);
  request.remove(KEY_HAVING);
  request.remove(KEY_ORDER);
  Map<String,Object> tableWhere=new LinkedHashMap<String,Object>();
  Set<String> set=request.keySet();
  if (method == POST) {
    if (set != null && set.isEmpty() == false) {
      List<Object> idList;
      if (id != null) {
        if (idIn != null) {
          throw new IllegalArgumentException("POST??? id ? id{} ??????!");
        }
        idList=new ArrayList<Object>(1);
        idList.add(id);
      }
 else {
        idList=new ArrayList<Object>((JSONArray)idIn);
      }
      String[] columns=set.toArray(new String[]{});
      Collection<Object> valueCollection=request.values();
      Object[] values=valueCollection == null ? null : valueCollection.toArray();
      if (values == null || values.length != columns.length) {
        throw new Exception("???????:\n" + TAG + " newSQLConfig  values == null || values.length != columns.length !");
      }
      column=idKey + "," + StringUtil.getString(columns);
      final int size=columns.length + 1;
      List<List<Object>> valuess=new ArrayList<>(idList.size());
      List<Object> items;
      for (int i=0; i < idList.size(); i++) {
        items=new ArrayList<>(size);
        items.add(idList.get(i));
        for (int j=1; j < size; j++) {
          items.add(values[j - 1]);
        }
        valuess.add(items);
      }
      config.setValues(valuess);
    }
  }
 else {
    final boolean isWhere=method != PUT;
    List<String> whereList=null;
    Map<String,List<String>> combineMap=new LinkedHashMap<>();
    List<String> andList=new ArrayList<>();
    List<String> orList=new ArrayList<>();
    List<String> notList=new ArrayList<>();
    if (id != null) {
      tableWhere.put(idKey,id);
      andList.add(idKey);
    }
    if (idIn != null) {
      tableWhere.put(idInKey,idIn);
      andList.add(idInKey);
    }
    String[] ws=StringUtil.split(combine);
    if (ws != null) {
      if (method == DELETE || method == GETS || method == HEADS) {
        throw new IllegalArgumentException("DELETE,GETS,HEADS ?????? @combine:\"conditons\" !");
      }
      whereList=new ArrayList<>();
      String w;
      for (int i=0; i < ws.length; i++) {
        w=ws[i];
        if (w != null) {
          if (w.startsWith("&")) {
            w=w.substring(1);
            andList.add(w);
          }
 else           if (w.startsWith("|")) {
            if (method == PUT) {
              throw new IllegalArgumentException(table + ":{} ?? @combine:value ??value??? " + ws[i] + " ????" + "PUT??? @combine:\"key0,key1,...\" ???? |key ? !key !");
            }
            w=w.substring(1);
            orList.add(w);
          }
 else           if (w.startsWith("!")) {
            if (method == PUT) {
              throw new IllegalArgumentException(table + ":{} ?? @combine:value ??value??? " + ws[i] + " ????" + "PUT??? @combine:\"key0,key1,...\" ???? |key ? !key !");
            }
            w=w.substring(1);
            notList.add(w);
          }
 else {
            orList.add(w);
          }
          if (w.isEmpty()) {
            throw new IllegalArgumentException(table + ":{} ?? @combine:value ??value??? " + ws[i] + " ???????????");
          }
 else {
            if (idKey.equals(w) || idInKey.equals(w) || userIdKey.equals(w) || userIdInKey.equals(w)) {
              throw new UnsupportedOperationException(table + ":{} ?? @combine:value ??value? " + ws[i] + " ????" + "???? [" + idKey + ", " + idInKey + ", " + userIdKey + ", " + userIdInKey + "] ???????");
            }
          }
          whereList.add(w);
        }
        if (request.containsKey(w) == false) {
          throw new IllegalArgumentException(table + ":{} ?? @combine:value ??value? " + ws[i] + " ??? " + w + " ??????");
        }
      }
    }
    Map<String,Object> tableContent=new LinkedHashMap<String,Object>();
    Object value;
    for (    String key : set) {
      value=request.get(key);
      if (value instanceof Map) {
        throw new IllegalArgumentException("??? " + key + " ???key?value??? {JSONObject} !");
      }
      if (isWhere) {
        tableWhere.put(key,value);
        if (whereList == null || whereList.contains(key) == false) {
          andList.add(key);
        }
      }
 else       if (whereList != null && whereList.contains(key)) {
        tableWhere.put(key,value);
      }
 else {
        tableContent.put(key,value);
      }
    }
    combineMap.put("&",andList);
    combineMap.put("|",orList);
    combineMap.put("!",notList);
    config.setCombine(combineMap);
    config.setContent(tableContent);
  }
  List<String> cs=new ArrayList<>();
  String[] fks=StringUtil.split(column,";");
  if (fks != null) {
    String[] ks;
    for (    String fk : fks) {
      if (fk.contains("(")) {
        cs.add(fk);
      }
 else {
        ks=StringUtil.split(fk);
        if (ks != null && ks.length > 0) {
          cs.addAll(Arrays.asList(ks));
        }
      }
    }
  }
  config.setExplain(explain);
  config.setCache(cache);
  config.setFrom(from);
  config.setColumn(column == null ? null : cs);
  config.setWhere(tableWhere);
  config.setId(id);
  config.setRole(role);
  config.setGroup(group);
  config.setHaving(having);
  config.setOrder(order);
  request.put(idKey,id);
  request.put(idInKey,idIn);
  request.put(KEY_DATABASE,database);
  request.put(KEY_ROLE,role);
  request.put(KEY_EXPLAIN,explain);
  request.put(KEY_CACHE,cache);
  request.put(KEY_SCHEMA,schema);
  request.put(KEY_COMBINE,combine);
  request.put(KEY_FROM,from);
  request.put(KEY_COLUMN,column);
  request.put(KEY_GROUP,group);
  request.put(KEY_HAVING,having);
  request.put(KEY_ORDER,order);
  return config;
}
