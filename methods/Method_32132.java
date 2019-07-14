private static Map<String,DateTimeZone> buildDefaultTimeZoneNames(){
  Map<String,DateTimeZone> map=new LinkedHashMap<String,DateTimeZone>();
  map.put("UT",DateTimeZone.UTC);
  map.put("UTC",DateTimeZone.UTC);
  map.put("GMT",DateTimeZone.UTC);
  put(map,"EST","America/New_York");
  put(map,"EDT","America/New_York");
  put(map,"CST","America/Chicago");
  put(map,"CDT","America/Chicago");
  put(map,"MST","America/Denver");
  put(map,"MDT","America/Denver");
  put(map,"PST","America/Los_Angeles");
  put(map,"PDT","America/Los_Angeles");
  return Collections.unmodifiableMap(map);
}
