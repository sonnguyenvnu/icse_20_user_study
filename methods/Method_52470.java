/** 
 * Whether an auxclasspath has been configured or not. This can be used to enable/disable more detailed symbol table analysis and type resolution can be used - or to fall back to more simple implementation.
 * @return <code>true</code> if the auxclasspath is configured and types canbe resolved reliably.
 * @see #resolveType(String)
 */
public boolean hasAuxclasspath(){
  return types.hasAuxclasspath();
}
