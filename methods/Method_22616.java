/** 
 * Checks coreLibraries and contribLibraries for a library with the specified name
 * @param libName the name of the library to find
 * @return the Library or null if not found
 */
public Library findLibraryByName(String libName){
  for (  Library lib : this.coreLibraries) {
    if (libName.equals(lib.getName()))     return lib;
  }
  for (  Library lib : this.contribLibraries) {
    if (libName.equals(lib.getName()))     return lib;
  }
  return null;
}
