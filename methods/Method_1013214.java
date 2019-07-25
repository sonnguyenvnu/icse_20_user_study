/** 
 * Find the ModulePointer that the String modName resolves to; Return null if either modName is not found in the context or if it is found and resolves to null, i.e. is not yet resolved.
 */
ModulePointer resolve(String modName){
  return (ModulePointer)context.get(modName);
}
