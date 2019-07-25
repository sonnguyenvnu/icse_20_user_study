/** 
 * ??
 * @param obj
 * @param i
 */
@SuppressWarnings("unchecked") private Object inject(Object obj,int i){
  String key=keys[i];
  if (key.indexOf('[') == 0) {
    List<Object> list=new ArrayList<Object>();
    if (obj != null) {
      list=(List<Object>)obj;
    }
    injectList(list,i,fetchIndex(key));
    return list;
  }
  int pos=key.indexOf('[');
  if (pos > 0) {
    Map<String,Object> map=new LinkedHashMap<String,Object>();
    if (obj != null) {
      map=(Map<String,Object>)obj;
    }
    String k=key.substring(0,pos);
    if (!map.containsKey(k)) {
      map.put(k,new ArrayList<Object>());
    }
    int[] index=fetchIndex(key.substring(key.indexOf('['),key.length()));
    injectList((List<Object>)map.get(k),i,index);
    return map;
  }
  if (obj instanceof List) {
    try {
      int index=Integer.parseInt(keys[i]);
      injectList((List<Object>)obj,i,Nums.array(index));
      return obj;
    }
 catch (    Exception e) {
      throw new RuntimeException("???????!");
    }
  }
  return injectMap(obj,i);
}
