/** 
 * Discard tracked association between file and modules. Does nothing if no association for the file is known.
 * @param file origin of a module or few modules
 */
public void forget(@NotNull IFile file){
  final Set<IFile> files2Remove=new THashSet<>();
  for (  IFile moduleFile : myFile2Module.keySet()) {
    if (isAncestor(file,moduleFile)) {
      files2Remove.add(moduleFile);
    }
  }
  for (  IFile moduleFile : files2Remove) {
    myFile2Module.remove(moduleFile);
  }
}
