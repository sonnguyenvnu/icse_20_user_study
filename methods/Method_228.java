/** 
 * True when this type's bindings use Resource directly instead of Context. 
 */
private boolean hasResourceBindingsNeedingResource(int sdk){
  for (  ResourceBinding binding : resourceBindings) {
    if (binding.requiresResources(sdk)) {
      return true;
    }
  }
  return false;
}
