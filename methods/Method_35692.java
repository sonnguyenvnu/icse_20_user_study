private URI getUriFor(ZipEntry jarEntry){
  try {
    return Resources.getResource(jarEntry.getName()).toURI();
  }
 catch (  URISyntaxException e) {
    return throwUnchecked(e,URI.class);
  }
}
