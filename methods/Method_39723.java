/** 
 * Returns  {@code true} if we are scanning the top-level type and notthe inner ones, like generics.
 */
private boolean isTopLevelType(){
  return argumentStack == 0;
}
