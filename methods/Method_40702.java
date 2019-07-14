/** 
 * Look for a binding named  {@code name} and if found, return its type.
 */
@Nullable public Type lookupType(String name){
  List<Binding> bs=lookup(name);
  if (bs == null) {
    return null;
  }
 else {
    return makeUnion(bs);
  }
}
