public static Path resolve(Path path,final String... childs){
  for (  String child : childs) {
    path=resolve(path,child);
  }
  return path;
}
