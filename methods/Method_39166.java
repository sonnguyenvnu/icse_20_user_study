/** 
 * Wraps action class and returns <code>MethRef</code> object (proxified target) so user can choose the method.
 */
protected <T>Methref<T> wrapTargetToMethref(final Class<T> target){
  return Methref.of(target);
}
