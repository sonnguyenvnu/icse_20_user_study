private static URL[] fileToURL(List<File> files) throws IOException {
  List<URL> urlList=new ArrayList<>();
  for (  File f : files) {
    urlList.add(f.toURI().toURL());
  }
  return urlList.toArray(new URL[0]);
}
