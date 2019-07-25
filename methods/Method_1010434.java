/** 
 * @return a set of changed modules
 */
@NotNull public Set<SModule> write(List<CompilationResult> results,ClassesErrorsTracker errorsTracker){
  updateClassFile2BytesMap(results);
  for (  CompilationResult result : results) {
    for (    ClassFile cf : result.getClassFiles()) {
      writeClassFile(cf,errorsTracker);
    }
  }
  return myChangedModulesTracker.getModules();
}
