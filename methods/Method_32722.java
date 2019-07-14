/** 
 * Loads the zone info map.
 * @param in  the input stream
 * @return the map
 */
private static Map<String,Object> loadZoneInfoMap(InputStream in) throws IOException {
  Map<String,Object> map=new ConcurrentHashMap<String,Object>();
  DataInputStream din=new DataInputStream(in);
  try {
    readZoneInfoMap(din,map);
  }
  finally {
    try {
      din.close();
    }
 catch (    IOException ex) {
    }
  }
  map.put("UTC",new SoftReference<DateTimeZone>(DateTimeZone.UTC));
  return map;
}
