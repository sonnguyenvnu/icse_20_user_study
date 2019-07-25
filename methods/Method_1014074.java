private ConfigDescription resolve(URI configDescriptionURI,Locale locale){
  if (configDescriptionURI == null) {
    return null;
  }
  return configDescriptionRegistry != null ? configDescriptionRegistry.getConfigDescription(configDescriptionURI,locale) : null;
}
