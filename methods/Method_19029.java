private static ComponentRenderInfo.Builder addCustomAttributes(ComponentRenderInfo.Builder builder,@Nullable Map<String,Object> attributes,SectionContext c){
  if (ComponentsConfiguration.isRenderInfoDebuggingEnabled()) {
    builder.debugInfo(SONAR_SECTIONS_DEBUG_INFO_TAG,c.getSectionScope());
  }
  if (attributes == null) {
    return builder;
  }
  for (  Map.Entry<String,Object> entry : attributes.entrySet()) {
    builder.customAttribute(entry.getKey(),entry.getValue());
  }
  return builder;
}
