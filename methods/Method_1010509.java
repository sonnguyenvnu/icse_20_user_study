/** 
 * Associates given module with a file. Multiple modules per single file are allowed. Multiple registration of the same File-Module pair is tolerated (XXX this is to avoid massive SLibrary refactoring, which may read same module and file).
 * @param file   origin of a module
 * @param module module read from the file
 */
public void track(@NotNull IFile file,@NotNull SModule module){
  Set<SModuleReference> modules=myFile2Module.computeIfAbsent(file,k -> new THashSet<>());
  modules.add(module.getModuleReference());
}
