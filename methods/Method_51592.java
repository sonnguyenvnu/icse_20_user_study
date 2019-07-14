private static URL[] initURLs(String classpath) throws IOException {
  if (classpath == null) {
    throw new IllegalArgumentException("classpath argument cannot be null");
  }
  final List<URL> urls=new ArrayList<>();
  if (classpath.startsWith("file://")) {
    addFileURLs(urls,new URL(classpath));
  }
 else {
    addClasspathURLs(urls,classpath);
  }
  return urls.toArray(new URL[0]);
}
