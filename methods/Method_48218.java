/** 
 * Whether this namespace or any parent namespace is an umbrella namespace.
 * @return
 */
public boolean hasUmbrella(){
  return isUmbrella() || (!isRoot() && getNamespace().hasUmbrella());
}
