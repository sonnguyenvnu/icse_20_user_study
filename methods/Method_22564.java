/** 
 * Add the packages provided by this library to the master list that maps imports to specific libraries.
 * @param importToLibraryTable mapping from package names to Library objects
 */
public void addPackageList(Map<String,List<Library>> importToLibraryTable){
  for (  String pkg : packageList) {
    List<Library> libraries=importToLibraryTable.get(pkg);
    if (libraries == null) {
      libraries=new ArrayList<Library>();
      importToLibraryTable.put(pkg,libraries);
    }
 else {
      if (Base.DEBUG) {
        System.err.println("The library found in");
        System.err.println(getPath());
        System.err.println("conflicts with");
        for (        Library library : libraries) {
          System.err.println(library.getPath());
        }
        System.err.println("which already define(s) the package " + pkg);
        System.err.println("If you have a line in your sketch that reads");
        System.err.println("import " + pkg + ".*;");
        System.err.println("Then you'll need to first remove one of those libraries.");
        System.err.println();
      }
    }
    libraries.add(this);
  }
}
