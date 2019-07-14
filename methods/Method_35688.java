@Override public InputStream getStream(){
  try {
    return uri.toURL().openStream();
  }
 catch (  IOException e) {
    return throwUnchecked(e,InputStream.class);
  }
}
