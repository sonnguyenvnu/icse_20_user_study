private void addEnvPath(){
  String path=System.getenv("RUBYLIB");
  if (path != null) {
    String[] segments=path.split(":");
    for (    String p : segments) {
      addPath(p);
    }
  }
}
