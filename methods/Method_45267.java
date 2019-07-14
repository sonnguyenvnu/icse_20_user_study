public static ResourceConfigApplier cookieConfigApplier(final String key,final Resource cookieResource){
  return new EmbeddedResourceConfigApplier(cookieResource){
    @Override protected Resource newResource(    final MocoConfig config){
      return cookieResource(key,cookieResource.apply(config));
    }
  }
;
}
