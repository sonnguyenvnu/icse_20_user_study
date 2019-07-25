@Override public JsonResult execute(){
  String tzVersion=ZoneRulesProvider.getVersions("UTC").firstKey();
  Instant now=Instant.now();
  Map<String,Integer> tzOffsets=new TreeMap<>();
  for (  String tz : ZoneId.getAvailableZoneIds()) {
    if (!tz.contains("SystemV")) {
      int offset=ZoneId.of(tz).getRules().getOffset(now).getTotalSeconds();
      tzOffsets.put(tz,offset);
    }
  }
  TimeZonesData output=new TimeZonesData(tzVersion,tzOffsets);
  return new JsonResult(output);
}
