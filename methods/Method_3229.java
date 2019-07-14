/** 
 * ???????
 * @param what
 * @param with
 * @return
 */
public float similarity(String what,String with){
  Vector A=query(what);
  if (A == null)   return -1f;
  Vector B=query(with);
  if (B == null)   return -1f;
  return A.cosineForUnitVector(B);
}
