protected static List<File> newList(String[] paths){
  if (paths == null) {
    return Collections.emptyList();
  }
 else {
    ArrayList<File> files=new ArrayList<>(paths.length);
    for (    String path : paths) {
      files.add(new File(path));
    }
    return files;
  }
}
