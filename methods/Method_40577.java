public void analyze(List<String> paths){
  for (  String path : paths) {
    loadFileRecursive(path);
  }
}
