/** 
 * ??????
 * @return
 */
public Integer getLargestValueId(){
  if (emits == null || emits.size() == 0)   return null;
  return emits.iterator().next();
}
