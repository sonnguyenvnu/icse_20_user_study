/** 
 * Create a plausible URI to use in  {@link #compilesWithFix}. 
 */
@VisibleForTesting static URI sourceURI(URI uri){
  if (!uri.getScheme().equals("jar")) {
    return uri;
  }
  try {
    return URI.create("file:/" + ((JarURLConnection)uri.toURL().openConnection()).getEntryName());
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
}
