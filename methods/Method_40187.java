/** 
 * Look for a attribute named  {@code attr} and if found, return its type.
 */
@Nullable public Type lookupAttrType(String attr){
  Set<Binding> bs=lookupAttr(attr);
  if (bs == null) {
    return null;
  }
 else {
    return makeUnion(bs);
  }
}
