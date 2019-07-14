public InputStream loadClassPathResourceAsStream(final String name) throws IOException {
  final URL resource=classLoader.getResource(name);
  if (resource == null) {
    return null;
  }
 else {
    final URLConnection connection=resource.openConnection();
    connection.setUseCaches(false);
    return connection.getInputStream();
  }
}
