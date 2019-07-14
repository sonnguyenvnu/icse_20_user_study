/** 
 * Opens a resource from file or classpath.
 * @param name  the name to open
 * @return the input stream
 * @throws IOException if an error occurs
 */
@SuppressWarnings("resource") private InputStream openResource(String name) throws IOException {
  InputStream in;
  if (iFileDir != null) {
    in=new FileInputStream(new File(iFileDir,name));
  }
 else {
    final String path=iResourcePath.concat(name);
    in=AccessController.doPrivileged(new PrivilegedAction<InputStream>(){
      public InputStream run(){
        if (iLoader != null) {
          return iLoader.getResourceAsStream(path);
        }
 else {
          return ClassLoader.getSystemResourceAsStream(path);
        }
      }
    }
);
    if (in == null) {
      StringBuilder buf=new StringBuilder(40).append("Resource not found: \"").append(path).append("\" ClassLoader: ").append(iLoader != null ? iLoader.toString() : "system");
      throw new IOException(buf.toString());
    }
  }
  return in;
}
