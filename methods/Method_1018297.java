/** 
 * Repackage to the given destination so that it can be launched using ' {@literal java -jar}'.
 * @param appDestination the executable fat jar's destination
 * @param moduleDestination the 'plug-in' module jar's destination
 * @param libraries the libraries required to run the archive
 * @throws IOException if the file cannot be repackaged
 */
public void repackage(File appDestination,File moduleDestination,Libraries libraries) throws IOException {
  if (appDestination == null || appDestination.isDirectory() || moduleDestination == null || moduleDestination.isDirectory()) {
    throw new IllegalArgumentException("Invalid destination");
  }
  if (libraries == null) {
    throw new IllegalArgumentException("Libraries must not be null");
  }
  if (alreadyRepackaged()) {
    return;
  }
  executableFatJar=appDestination;
  pluginModuleJar=moduleDestination;
  libraries.doWithLibraries(new LibraryCallback(){
    @Override public void library(    Library library) throws IOException {
      if (LibraryScope.PROVIDED.equals(library.getScope()) && !isPackageProvided()) {
        return;
      }
      if (!isZip(library.getFile())) {
        return;
      }
      try (JarFile jarFile=new JarFile(library.getFile())){
        if (isArkContainer(jarFile)) {
          if (arkContainerLibrary != null) {
            throw new RuntimeException("duplicate SOFAArk Container dependency");
          }
          library.setScope(LibraryScope.CONTAINER);
          arkContainerLibrary=library;
        }
 else         if (isArkModule(jarFile)) {
          library.setScope(LibraryScope.MODULE);
          arkModuleLibraries.add(library);
        }
 else         if (isArkPlugin(jarFile)) {
          library.setScope(LibraryScope.PLUGIN);
          arkPluginLibraries.add(library);
        }
 else {
          standardLibraries.add(library);
        }
      }
     }
  }
);
  repackageModule();
  repackageApp();
  removeArkBizJar();
}
