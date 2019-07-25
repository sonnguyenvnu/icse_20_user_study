@SuppressWarnings("rawtypes") public Object parse(Object obj){
  if (null == obj) {
    return null;
  }
 else   if (obj instanceof ObjCompileImpl) {
    return ((ObjCompileImpl)obj).parse(null);
  }
 else   if (obj instanceof Class) {
    return obj;
  }
 else   if (obj instanceof Mirror) {
    return ((Mirror<?>)obj).getType().getName();
  }
 else {
    Mirror mr=Mirror.me(obj.getClass());
    if (mr.isEnum()) {
      return obj;
    }
 else     if (mr.isNumber() || mr.isBoolean()) {
      return obj;
    }
 else     if (mr.isStringLike() || mr.isChar()) {
      return obj;
    }
 else     if (mr.isDateTimeLike()) {
      return obj;
    }
 else {
      if (memo.containsKey(obj)) {
        return memo.get(obj);
      }
 else {
        if (obj instanceof Collection || obj.getClass().isArray()) {
          List<Object> list=new ArrayList<Object>();
          memo.put(obj,list);
          if (obj instanceof Collection) {
            return coll2Json((Collection)obj,list);
          }
          return array2Json(obj,list);
        }
 else {
          Map<String,Object> map=new LinkedHashMap<String,Object>();
          memo.put(obj,map);
          if (obj instanceof Map) {
            return map2Json((Map)obj,map);
          }
          return pojo2Json(obj,map);
        }
      }
    }
  }
}
