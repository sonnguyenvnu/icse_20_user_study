@Value.Derived public Optional<Image> nestedThumbnail(){
  if (preview() == null || preview().images() == null || preview().images().get(0).source() == null)   return Optional.absent();
  return Optional.of(preview().images().get(0).source());
}
