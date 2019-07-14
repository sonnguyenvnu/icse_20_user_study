/** 
 * Attempts to load the resource from file, a URL or the claspath <p> Caller is responsible for closing the  {@link InputStream}.
 * @param name The resource to attempt and load
 * @return InputStream
 * @throws RuleSetNotFoundException
 */
public InputStream loadResourceAsStream(final String name) throws RuleSetNotFoundException {
  final File file=new File(name);
  if (file.exists()) {
    try {
      return Files.newInputStream(file.toPath());
    }
 catch (    final IOException e) {
      throw new RuntimeException(e);
    }
  }
  try {
    final HttpURLConnection connection=(HttpURLConnection)new URL(name).openConnection();
    connection.setConnectTimeout(TIMEOUT);
    connection.setReadTimeout(TIMEOUT);
    return connection.getInputStream();
  }
 catch (  final Exception e) {
    try {
      return loadClassPathResourceAsStream(name);
    }
 catch (    final IOException ignored) {
    }
  }
  throw new RuleSetNotFoundException("Can't find resource " + name + ". Make sure the resource is a valid file or URL or is on the CLASSPATH");
}
