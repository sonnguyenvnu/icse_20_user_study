@Override protected ConfigDescription localize(Bundle bundle,ConfigDescription configDescription,Locale locale){
  ConfigI18nLocalizationService configI18nLocalizerService=getConfigI18nLocalizerService();
  if (configI18nLocalizerService == null) {
    return null;
  }
  return configI18nLocalizerService.getLocalizedConfigDescription(bundle,configDescription,locale);
}
