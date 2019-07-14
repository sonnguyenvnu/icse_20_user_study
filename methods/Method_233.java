/** 
 * True if this binding requires a view. Otherwise only a context is needed. 
 */
private boolean constructorNeedsView(){
  return hasViewBindings() || (parentBinding != null && parentBinding.constructorNeedsView());
}
