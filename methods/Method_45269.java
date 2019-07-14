public static ResourceConfigApplier uriConfigApplier(final String id,final String uri){
  return new SelfResourceConfigApplier(id){
    @Override @SuppressWarnings("unchecked") protected Resource newResource(    final MocoConfig config){
      return uriResource((String)config.apply(uri));
    }
  }
;
}
