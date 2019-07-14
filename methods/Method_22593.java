public void rebuildLibraryList(){
  Map<String,List<Library>> newTable=new HashMap<>();
  Library core=getCoreLibrary();
  if (core != null) {
    core.addPackageList(newTable);
  }
  coreLibraries=Library.list(librariesFolder);
  File contribLibrariesFolder=Base.getSketchbookLibrariesFolder();
  contribLibraries=Library.list(contribLibrariesFolder);
  List<Library> foundationLibraries=new ArrayList<>();
  for (  Library lib : contribLibraries) {
    if (lib.isFoundation()) {
      foundationLibraries.add(lib);
    }
  }
  coreLibraries.addAll(foundationLibraries);
  contribLibraries.removeAll(foundationLibraries);
  for (  Library lib : coreLibraries) {
    lib.addPackageList(newTable);
  }
  for (  Library lib : contribLibraries) {
    lib.addPackageList(newTable);
  }
  importToLibraryTable=Collections.unmodifiableMap(newTable);
  if (base != null) {
    base.getEditors().forEach(Editor::librariesChanged);
  }
}
