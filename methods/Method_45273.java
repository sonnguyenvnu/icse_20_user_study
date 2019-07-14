public static ContentResource classpathFileResource(final Resource filename,final Charset charset){
  return contentResource(id("pathresource"),DO_NOTHING_APPLIER,new ClasspathFileResourceReader(filename,charset));
}
