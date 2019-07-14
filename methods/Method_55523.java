@Nullable static Path findFile(String path,String file){
  for (  String directory : PATH_SEPARATOR.split(path)) {
    Path p=Paths.get(directory,file);
    if (Files.isReadable(p)) {
      return p;
    }
  }
  return null;
}
