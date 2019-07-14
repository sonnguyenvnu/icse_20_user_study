public Type requireFile(String headName){
  List<String> loadPath=getLoadPath();
  for (  String p : loadPath) {
    String trial=_.makePathString(p,headName + suffix);
    if (new File(trial).exists()) {
      return loadFile(trial);
    }
  }
  return null;
}
