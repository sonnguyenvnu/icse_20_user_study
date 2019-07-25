public void forget(@NotNull IFile file,@NotNull SModuleReference module){
  Set<SModuleReference> modules=myFile2Module.get(file);
  if (modules == null) {
    return;
  }
  if (modules.remove(module)) {
    if (modules.isEmpty()) {
      myFile2Module.remove(file);
    }
  }
}
