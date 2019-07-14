/** 
 * Adds action violation.
 */
protected void addViolation(final String name,final Object invalidValue){
  prepareValidator();
  vtor.addViolation(new Violation(name,this,invalidValue));
}
