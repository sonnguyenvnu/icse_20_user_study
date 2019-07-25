/** 
 * Validates that the instance is valid for the given number of replicas in an index.
 */
public boolean validate(final int numberOfReplicas){
  assert numberOfReplicas >= 0;
  return value <= numberOfReplicas + 1;
}
