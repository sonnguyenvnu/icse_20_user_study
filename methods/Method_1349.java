/** 
 * Assert that there is a valid referenced value. Throw a NullReferenceException otherwise
 * @throws NullReferenceException , if the reference is invalid (i.e.) the underlying value is null
 */
private void ensureValid(){
  if (!isValid(this)) {
    throw new NullReferenceException();
  }
}
