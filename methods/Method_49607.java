@Override public boolean exists() throws BackendException {
  if (Files.exists(Paths.get(basePath))) {
    try (final DirectoryStream<Path> dirStream=Files.newDirectoryStream(Paths.get(basePath))){
      return dirStream.iterator().hasNext();
    }
 catch (    final IOException e) {
      throw new PermanentBackendException("Could not read lucene directory: " + basePath,e);
    }
  }
 else {
    return false;
  }
}
