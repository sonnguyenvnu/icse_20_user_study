/** 
 * Converts this promise to an operation, by effectively discarding the result.
 * @return an operation
 */
default Operation operation(){
  return operation(Action.noop());
}
