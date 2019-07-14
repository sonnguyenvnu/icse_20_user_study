/** 
 * True if controller classes have been parsed and their java classes need to be written. We need to wait for other models to finish being generated first so we can resolve generated model references.
 * @see #resolveGeneratedModelsAndWriteJava(List)
 */
boolean hasControllersToGenerate(){
  return !controllerClassMap.isEmpty();
}
