/** 
 * Adds to  {@code errors} if the test class's single constructor takesparameters (do not override)
 */
protected void validateZeroArgConstructor(List<Throwable> errors){
  if (!getTestClass().isANonStaticInnerClass() && hasOneConstructor() && (getTestClass().getOnlyConstructor().getParameterTypes().length != 0)) {
    String gripe="Test class should have exactly one public zero-argument constructor";
    errors.add(new Exception(gripe));
  }
}
