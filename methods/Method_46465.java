/** 
 * ????????????
 * @return ??????
 */
public static DTXLocalContext getOrNew(){
  if (currentLocal.get() == null) {
    currentLocal.set(new DTXLocalContext());
  }
  return currentLocal.get();
}
