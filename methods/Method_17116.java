/** 
 * Ensures that the state expression is true. 
 */
static void requireState(boolean expression,String template,@Nullable Object... args){
  if (!expression) {
    throw new IllegalStateException(String.format(template,args));
  }
}
