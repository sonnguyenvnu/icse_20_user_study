private static InputStream toStream(final Resource resource){
  return checkNotNull(resource,"resource should not be null").readFor(null).toInputStream();
}
