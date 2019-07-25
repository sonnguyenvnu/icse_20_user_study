public static Path relativize(Path path,List<Path> fileDirectories){
  if (!path.isAbsolute()) {
    return path;
  }
  for (  Path directory : fileDirectories) {
    if (path.startsWith(directory)) {
      return directory.relativize(path);
    }
  }
  return path;
}
