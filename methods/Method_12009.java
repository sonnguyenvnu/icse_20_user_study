/** 
 * Adds to  {@code errors} if the test class has more than one constructor(do not override)
 */
protected void validateOnlyOneConstructor(List<Throwable> errors){
  if (!hasOneConstructor()) {
    String gripe="Test class should have exactly one public constructor";
    errors.add(new Exception(gripe));
  }
}
