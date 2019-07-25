public boolean check(HttpResponse resp,File file,String... bases) throws IOException, Pausable {
  try {
    String path=file.getCanonicalPath();
    for (    String base : bases)     if (path.startsWith(base))     return true;
  }
 catch (  Exception ex) {
  }
  return false;
}
