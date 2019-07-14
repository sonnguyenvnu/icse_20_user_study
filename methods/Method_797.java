@SuppressWarnings({"rawtypes","unchecked"}) public static <T>T cast(Object obj,ParameterizedType type,ParserConfig mapping){
  Type rawTye=type.getRawType();
  if (rawTye == List.class || rawTye == ArrayList.class) {
    Type itemType=type.getActualTypeArguments()[0];
    if (obj instanceof List) {
      List listObj=(List)obj;
      List arrayList=new ArrayList(listObj.size());
      for (int i=0; i < listObj.size(); i++) {
        Object item=listObj.get(i);
        Object itemValue;
        if (itemType instanceof Class) {
          if (item != null && item.getClass() == JSONObject.class) {
            itemValue=((JSONObject)item).toJavaObject((Class<T>)itemType,mapping,0);
          }
 else {
            itemValue=cast(item,(Class<T>)itemType,mapping);
          }
        }
 else {
          itemValue=cast(item,itemType,mapping);
        }
        arrayList.add(itemValue);
      }
      return (T)arrayList;
    }
  }
  if (rawTye == Set.class || rawTye == HashSet.class || rawTye == TreeSet.class || rawTye == Collection.class || rawTye == List.class || rawTye == ArrayList.class) {
    Type itemType=type.getActualTypeArguments()[0];
    if (obj instanceof Iterable) {
      Collection collection;
      if (rawTye == Set.class || rawTye == HashSet.class) {
        collection=new HashSet();
      }
 else       if (rawTye == TreeSet.class) {
        collection=new TreeSet();
      }
 else {
        collection=new ArrayList();
      }
      for (Iterator it=((Iterable)obj).iterator(); it.hasNext(); ) {
        Object item=it.next();
        Object itemValue;
        if (itemType instanceof Class) {
          if (item != null && item.getClass() == JSONObject.class) {
            itemValue=((JSONObject)item).toJavaObject((Class<T>)itemType,mapping,0);
          }
 else {
            itemValue=cast(item,(Class<T>)itemType,mapping);
          }
        }
 else {
          itemValue=cast(item,itemType,mapping);
        }
        collection.add(itemValue);
      }
      return (T)collection;
    }
  }
  if (rawTye == Map.class || rawTye == HashMap.class) {
    Type keyType=type.getActualTypeArguments()[0];
    Type valueType=type.getActualTypeArguments()[1];
    if (obj instanceof Map) {
      Map map=new HashMap();
      for (      Map.Entry entry : ((Map<?,?>)obj).entrySet()) {
        Object key=cast(entry.getKey(),keyType,mapping);
        Object value=cast(entry.getValue(),valueType,mapping);
        map.put(key,value);
      }
      return (T)map;
    }
  }
  if (obj instanceof String) {
    String strVal=(String)obj;
    if (strVal.length() == 0) {
      return null;
    }
  }
  if (type.getActualTypeArguments().length == 1) {
    Type argType=type.getActualTypeArguments()[0];
    if (argType instanceof WildcardType) {
      return (T)cast(obj,rawTye,mapping);
    }
  }
  if (rawTye == Map.Entry.class && obj instanceof Map && ((Map)obj).size() == 1) {
    Map.Entry entry=(Map.Entry)((Map)obj).entrySet().iterator().next();
    return (T)entry;
  }
  if (rawTye instanceof Class) {
    if (mapping == null) {
      mapping=ParserConfig.global;
    }
    ObjectDeserializer deserializer=mapping.getDeserializer(rawTye);
    if (deserializer != null) {
      String str=JSON.toJSONString(obj);
      DefaultJSONParser parser=new DefaultJSONParser(str,mapping);
      return (T)deserializer.deserialze(parser,type,null);
    }
  }
  throw new JSONException("can not cast to : " + type);
}
