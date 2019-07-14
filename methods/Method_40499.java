public List<String> getLoadedFiles(){
  List<String> files=new ArrayList<String>();
  for (  String file : moduleTable.keySet()) {
    if (file.startsWith("/")) {
      files.add(file);
    }
  }
  return files;
}
