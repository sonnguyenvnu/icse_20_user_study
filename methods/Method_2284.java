static FrescoContext resolveContext(ComponentContext context,@Nullable FrescoContext contextOverride){
  if (contextOverride != null) {
    return contextOverride;
  }
  return DefaultFrescoContext.get(context.getResources());
}
