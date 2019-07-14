private void copyProperties(InternalRelation to){
  for (  LongObjectCursor<Object> entry : getPropertyMap()) {
    PropertyKey type=tx().getExistingPropertyKey(entry.key);
    if (!(type instanceof ImplicitKey))     to.setPropertyDirect(type,entry.value);
  }
}
