/** 
 * ??????
 * @param parent
 * @param pathKeys
 * @return
 */
protected static Object getValue(JSONObject parent,String[] pathKeys){
  if (parent == null || pathKeys == null || pathKeys.length <= 0) {
    Log.w(TAG,"getChild  parent == null || pathKeys == null || pathKeys.length <= 0 >> return parent;");
    return parent;
  }
  final int last=pathKeys.length - 1;
  for (int i=0; i < last; i++) {
    if (parent == null) {
      break;
    }
    parent=getJSONObject(parent,pathKeys[i]);
  }
  return parent == null ? null : parent.get(pathKeys[last]);
}
