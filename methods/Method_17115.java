/** 
 * Ensures that the state expression is true. 
 */
static void requireState(boolean expression){
  if (!expression) {
    throw new IllegalStateException();
  }
}
