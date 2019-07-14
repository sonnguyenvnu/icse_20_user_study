@Nullable private static Path loadFromLibraryPath(String libName){
  String paths=Configuration.LIBRARY_PATH.get();
  if (paths == null) {
    return null;
  }
  return load(libName,Configuration.LIBRARY_PATH.getProperty(),paths);
}
