@Nullable public Type lookupAttrTypeTagged(String attr,String tag){
  return lookupAttrType(makeTagId(attr,tag));
}
