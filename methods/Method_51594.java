private static void addFileURLs(List<URL> urls,URL fileURL) throws IOException {
  try (BufferedReader in=new BufferedReader(new InputStreamReader(fileURL.openStream()))){
    String line;
    while ((line=in.readLine()) != null) {
      LOG.log(Level.FINE,"Read classpath entry line: <{0}>",line);
      line=line.trim();
      if (line.length() > 0) {
        LOG.log(Level.FINE,"Adding classpath entry: <{0}>",line);
        urls.add(createURLFromPath(line));
      }
    }
  }
 }
