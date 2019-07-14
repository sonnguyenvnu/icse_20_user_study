private boolean isInitPy(){
  String path=getFile();
  if (path == null) {
    return false;
  }
  return new File(path).getName().equals("__init__.py");
}
