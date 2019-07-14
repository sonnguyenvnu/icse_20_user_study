private void addPythonPath(){
  String path=System.getenv("PYTHONPATH");
  if (path != null) {
    String[] segments=path.split(":");
    for (    String p : segments) {
      addPath(p);
    }
  }
}
