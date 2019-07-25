@Override protected ThingType localize(Bundle bundle,ThingType thingType,Locale locale){
  if (thingTypeI18nLocalizationService == null) {
    return null;
  }
  return thingTypeI18nLocalizationService.createLocalizedThingType(bundle,thingType,locale);
}
