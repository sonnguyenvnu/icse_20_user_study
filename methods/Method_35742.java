public static InputStreamSource forURI(final URI uri){
  return new InputStreamSource(){
    @Override public InputStream getStream(){
      try {
        return uri == null ? null : new BufferedInputStream(uri.toURL().openStream());
      }
 catch (      IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
;
}
