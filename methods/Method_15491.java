/** 
 * @param key
 * @param value
 * @param isFirst 
 * @return
 * @throws Exception
 */
@Override public JSON onChildParse(int index,String key,JSONObject value) throws Exception {
  boolean isFirst=index <= 0;
  boolean isMain=isFirst && type == TYPE_ITEM;
  JSON child;
  boolean isEmpty;
  if (zuo.biao.apijson.JSONObject.isArrayKey(key)) {
    if (isMain) {
      throw new IllegalArgumentException(parentPath + "/" + key + ":{} ????" + "?? []:{} ???? key:{} ????? TableKey:{} ???? arrayKey[]:{} ?");
    }
    if (arrayConfig == null || arrayConfig.getPosition() == 0) {
      arrayCount++;
      int maxArrayCount=parser.getMaxArrayCount();
      if (arrayCount > maxArrayCount) {
        throw new IllegalArgumentException(path + " ??? " + key + ":{} ????? key[]:{} ????? " + arrayCount + " ??????? 0-" + maxArrayCount + " ? !");
      }
    }
    child=parser.onArrayParse(value,path,key,isSubquery);
    isEmpty=child == null || ((JSONArray)child).isEmpty();
  }
 else {
    boolean isTableKey=JSONRequest.isTableKey(Pair.parseEntry(key,true).getKey());
    if (type == TYPE_ITEM && isTableKey == false) {
      throw new IllegalArgumentException(parentPath + "/" + key + ":{} ????" + "?? []:{} ??? key:{} ????? TableKey:{} ? ?? arrayKey[]:{} ?");
    }
    if ((arrayConfig == null || arrayConfig.getPosition() == 0)) {
      objectCount++;
      int maxObjectCount=parser.getMaxObjectCount();
      if (objectCount > maxObjectCount) {
        throw new IllegalArgumentException(path + " ??? " + key + ":{} ??? key:{} ????? " + objectCount + " ??????? 0-" + maxObjectCount + " ? !");
      }
    }
    child=parser.onObjectParse(value,path,key,isMain ? arrayConfig.setType(SQLConfig.TYPE_ITEM_CHILD_0) : null,isSubquery);
    isEmpty=child == null || ((JSONObject)child).isEmpty();
    if (isFirst && isEmpty) {
      invalidate();
    }
  }
  Log.i(TAG,"onChildParse  ObjectParser.onParse  key = " + key + "; child = " + child);
  return isEmpty ? null : child;
}
