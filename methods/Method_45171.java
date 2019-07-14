public static ContentResource pathResource(final Resource filename,final Charset charset){
  return classpathFileResource(checkNotNull(filename,"Filename should not be null"),charset);
}
