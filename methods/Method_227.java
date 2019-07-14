/** 
 * True when this type's bindings use raw integer values instead of  {@code R} references. 
 */
private boolean hasUnqualifiedResourceBindings(){
  for (  ResourceBinding binding : resourceBindings) {
    if (!binding.id().qualifed) {
      return true;
    }
  }
  return false;
}
