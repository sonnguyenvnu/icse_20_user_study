public static ResourceConfigApplier templateConfigApplier(final ContentResource template,final ImmutableMap<String,? extends Variable> variables){
  return new EmbeddedResourceConfigApplier(template){
    @Override protected Resource newResource(    final MocoConfig config){
      return templateResource((ContentResource)template.apply(config),variables);
    }
  }
;
}
