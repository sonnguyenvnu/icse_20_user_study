protected boolean hasAllCanonicalTypes(){
  if (types.length == 0)   return false;
  for (  String typeName : types) {
    InternalRelationType type=QueryUtil.getType(tx,typeName);
    if (type != null && !type.isPropertyKey() && !type.multiplicity().isUnique(dir))     return false;
  }
  return true;
}
