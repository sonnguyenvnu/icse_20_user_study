/** 
 * Returns the value of merge of simple masks. If values of simple masks are not invalid then null is returned.
 */
private Integer merge(Integer m1,Integer m2,InterpreterContext instrCtx){
  if (isValidMaskValue(m1,instrCtx) && isValidMaskValue(m2,instrCtx))   return (m1 < m2) ? m1 : m2;
 else   return null;
}
