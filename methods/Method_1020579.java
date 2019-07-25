@Override public boolean accepts(ResourceLocation resourceLocation){
  return resourceLocation.getResourceDomain().equals("minecraftbyexample") && resourceLocation.getResourcePath().startsWith(SMART_MODEL_RESOURCE_LOCATION);
}
