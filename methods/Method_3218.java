/** 
 * ?????
 * @param what ???
 * @param with ????
 * @return ?????
 */
public float similarity(K what,K with){
  Vector vectorWhat=storage.get(what);
  if (vectorWhat == null) {
    return -1f;
  }
  Vector vectorWith=storage.get(with);
  if (vectorWith == null) {
    return -1f;
  }
  return vectorWhat.cosineForUnitVector(vectorWith);
}
