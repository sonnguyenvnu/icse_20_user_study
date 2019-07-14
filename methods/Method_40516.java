/** 
 * Look for a attribute named  {@code attr} and if found, return its type.
 */
public Type lookupAttrType(String attr){
  Binding b=lookupAttr(attr);
  if (b == null) {
    return null;
  }
 else {
    return b.getType();
  }
}
