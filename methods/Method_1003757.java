/** 
 * Creates a block that executes this action with the given value when called.
 * @param value the value to execute this action with when the block is executed
 * @return a new block
 */
default Block curry(T value){
  return () -> execute(value);
}
