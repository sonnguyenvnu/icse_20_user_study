/** 
 * Returns the first definition, which by convention is treated as the one that introduced the binding.
 */
public Def getSignatureNode(){
  return getDefs().isEmpty() ? null : getDefs().iterator().next();
}
