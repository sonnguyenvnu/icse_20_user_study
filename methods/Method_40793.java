public static void mergeDefault(Scope properties,Scope s){
  for (  String key : properties.keySet()) {
    Object defaultValue=properties.lookupPropertyLocal(key,"default");
    if (defaultValue == null) {
      continue;
    }
 else     if (defaultValue instanceof Value) {
      Value existing=s.lookup(key);
      if (existing == null) {
        s.putValue(key,(Value)defaultValue);
      }
    }
 else {
      _.abort("default value is not a value, shouldn't happen");
    }
  }
}
