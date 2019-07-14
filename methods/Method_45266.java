public static ResourceConfigApplier fileConfigApplier(final String id,final Resource file){
  return new SelfResourceConfigApplier(id){
    @Override @SuppressWarnings("unchecked") protected Resource newResource(    final MocoConfig config){
      return fileResource(file,null,config);
    }
  }
;
}
