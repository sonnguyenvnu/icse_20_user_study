/** 
 * Adds to  {@code errors} for each method annotated with {@code @Test}that is not a public, void instance method with no arguments.
 */
protected void validateTestMethods(List<Throwable> errors){
  validatePublicVoidNoArgMethods(Test.class,false,errors);
}
