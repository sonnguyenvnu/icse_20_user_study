/** 
 * @param key original key (may include platform extension)
 * @param value
 * @param specific where to put the key/value pairs for *this* platform
 * @return true if a platform-specific key
 */
static protected boolean isPlatformSpecific(String key,String value,Map<String,String> specific){
  for (  String platform : PConstants.platformNames) {
    String ext="." + platform;
    if (key.endsWith(ext)) {
      String thisPlatform=PConstants.platformNames[PApplet.platform];
      if (platform.equals(thisPlatform)) {
        key=key.substring(0,key.lastIndexOf(ext));
        specific.put(key,value);
      }
 else {
      }
      return true;
    }
  }
  return false;
}
