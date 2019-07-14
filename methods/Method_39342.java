/** 
 * Adds init methods.
 */
protected void addInitMethodPoints(final InitMethodPoint[] methods){
  if (initMethods == null) {
    initMethods=methods;
  }
 else {
    initMethods=ArraysUtil.join(initMethods,methods);
  }
}
