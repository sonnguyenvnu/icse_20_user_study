public static void mergeType(Scope properties,Scope s){
  for (  String key : properties.keySet()) {
    if (key.equals(Constants.RETURN_ARROW)) {
      continue;
    }
    Object type=properties.lookupPropertyLocal(key,"type");
    if (type == null) {
      continue;
    }
 else     if (type instanceof Value) {
      Value existing=s.lookup(key);
      if (existing == null) {
        s.putValue(key,(Value)type);
      }
    }
 else {
      _.abort("illegal type, shouldn't happen" + type);
    }
  }
}
