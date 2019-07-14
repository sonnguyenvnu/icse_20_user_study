/** 
 * Helper method for obtaining resources from a  {@link Filer}, taking care of some javac peculiarities.
 */
private static Optional<FileObject> getResource(final Filer filer,final JavaFileManager.Location location,final String packageName,final String filePath){
  try {
    final FileObject resource=filer.getResource(location,packageName,filePath);
    resource.openInputStream().close();
    return Optional.of(resource);
  }
 catch (  final Exception e) {
    if (!(e instanceof FileNotFoundException || e.getClass().getName().equals("com.sun.tools.javac.util.ClientCodeException"))) {
      throw new RuntimeException(String.format("Error opening resource %s/%s",packageName,filePath),e.getCause());
    }
    return Optional.empty();
  }
}
