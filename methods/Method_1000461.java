/** 
 * ????????????????????
 * @param tableName ????
 * @return ??????
 */
public static String render(Segment tableName){
  Object obj=get();
  if (null == obj || !tableName.hasKey())   return tableName.toString();
  Context context=Lang.context();
  if (isPrimitive(obj)) {
    for (    String key : tableName.keys())     context.set(key,obj);
  }
 else   if (obj instanceof Context) {
    for (    String key : tableName.keys())     context.set(key,((Context)obj).get(key));
  }
 else   if (obj instanceof Map<?,?>) {
    for (    String key : tableName.keys())     context.set(key,((Map<?,?>)obj).get(key));
  }
 else {
    Mirror<?> mirror=Mirror.me(obj);
    for (    String key : tableName.keys())     context.set(key,mirror.getValue(obj,key));
  }
  return tableName.render(context).toString();
}
