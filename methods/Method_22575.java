static public boolean hasMultipleArch(int platform,List<Library> libraries){
  return libraries.stream().anyMatch(library -> library.hasMultipleArch(platform));
}
