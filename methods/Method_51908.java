/** 
 * Returns true if the declared variable is initialized. Otherwise,  {@link #getInitializer()} returns null.
 */
public boolean hasInitializer(){
  return jjtGetNumChildren() > 1;
}
