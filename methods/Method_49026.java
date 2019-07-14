@Override public void setPropertyDirect(PropertyKey key,Object value){
  Preconditions.checkArgument(!(key instanceof ImplicitKey),"Cannot use implicit type [%s] when setting property",key.name());
  if (properties == EMPTY_PROPERTIES) {
    if (tx().getConfiguration().isSingleThreaded()) {
      properties=new HashMap<>(5);
    }
 else {
synchronized (this) {
        if (properties == EMPTY_PROPERTIES) {
          properties=Collections.synchronizedMap(new HashMap<PropertyKey,Object>(5));
        }
      }
    }
  }
  tx().checkPropertyConstraintForEdgeOrCreatePropertyConstraint(this,key);
  properties.put(key,value);
}
