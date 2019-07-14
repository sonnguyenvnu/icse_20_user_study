public static ResourceConfigApplier jsonConfigApplier(final Resource resource){
  return new EmbeddedResourceConfigApplier(resource){
    @Override protected Resource newResource(    final MocoConfig config){
      return jsonResource(resource.apply(config));
    }
  }
;
}
