/** 
 * ??????
 */
public static void makeNeverAppeared(){
  if (currentLocal.get() != null) {
    log.debug("clean thread local[{}]: {}",DTXLocalContext.class.getSimpleName(),cur());
    currentLocal.set(null);
  }
}
