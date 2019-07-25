/** 
 * Bind a module name to a particular ModulePointer, replacing any binding that is already there to the same modName.
 */
void bind(String modName,ModulePointer modPointer){
  context.put(modName,modPointer);
}
