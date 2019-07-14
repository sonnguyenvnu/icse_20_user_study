/** 
 * Adds destroy methods.
 */
protected void addDestroyMethodPoints(final DestroyMethodPoint[] methods){
  if (destroyMethods == null) {
    destroyMethods=methods;
  }
 else {
    destroyMethods=ArraysUtil.join(destroyMethods,methods);
  }
}
