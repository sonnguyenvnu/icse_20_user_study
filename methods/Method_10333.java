private List<BasicNameValuePair> getParamsList(String key,Object value){
  List<BasicNameValuePair> params=new LinkedList<BasicNameValuePair>();
  if (value instanceof Map) {
    Map map=(Map)value;
    List list=new ArrayList<Object>(map.keySet());
    if (list.size() > 0 && list.get(0) instanceof Comparable) {
      Collections.sort(list);
    }
    for (    Object nestedKey : list) {
      if (nestedKey instanceof String) {
        Object nestedValue=map.get(nestedKey);
        if (nestedValue != null) {
          params.addAll(getParamsList(key == null ? (String)nestedKey : String.format(Locale.US,"%s[%s]",key,nestedKey),nestedValue));
        }
      }
    }
  }
 else   if (value instanceof List) {
    List list=(List)value;
    int listSize=list.size();
    for (int nestedValueIndex=0; nestedValueIndex < listSize; nestedValueIndex++) {
      params.addAll(getParamsList(String.format(Locale.US,"%s[%d]",key,nestedValueIndex),list.get(nestedValueIndex)));
    }
  }
 else   if (value instanceof Object[]) {
    Object[] array=(Object[])value;
    int arrayLength=array.length;
    for (int nestedValueIndex=0; nestedValueIndex < arrayLength; nestedValueIndex++) {
      params.addAll(getParamsList(String.format(Locale.US,"%s[%d]",key,nestedValueIndex),array[nestedValueIndex]));
    }
  }
 else   if (value instanceof Set) {
    Set set=(Set)value;
    for (    Object nestedValue : set) {
      params.addAll(getParamsList(key,nestedValue));
    }
  }
 else {
    params.add(new BasicNameValuePair(key,value.toString()));
  }
  return params;
}
