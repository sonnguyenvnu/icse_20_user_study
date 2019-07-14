default Map<String,Object> cloneProperties(){
  Map<String,Object> target=new LinkedHashMap<>();
  Map<String,Object> old=getProperties();
  if (old == null || old.isEmpty()) {
    return target;
  }
  old.forEach((k,v) -> {
    if (v instanceof CloneableEntity) {
      target.put(k,((CloneableEntity)v).clone());
    }
 else {
      target.put(k,v);
    }
  }
);
  return target;
}
