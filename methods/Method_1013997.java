@Modified protected synchronized void modified(Map<String,Object> config){
  final String language=toStringOrNull(config.get(LANGUAGE));
  final String script=toStringOrNull(config.get(SCRIPT));
  final String region=toStringOrNull(config.get(REGION));
  final String variant=toStringOrNull(config.get(VARIANT));
  final String location=toStringOrNull(config.get(LOCATION));
  final String zoneId=toStringOrNull(config.get(TIMEZONE));
  final String measurementSystem=toStringOrNull(config.get(MEASUREMENT_SYSTEM));
  setTimeZone(zoneId);
  setLocation(location);
  setLocale(language,script,region,variant);
  setMeasurementSystem(measurementSystem);
}
