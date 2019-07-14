private synchronized String[] getNameSet(Locale locale,String id,String nameKey,boolean standardTime){
  if (locale == null || id == null || nameKey == null) {
    return null;
  }
  if (id.startsWith("Etc/")) {
    id=id.substring(4);
  }
  Map<String,Map<Boolean,Object>> byIdCache=iByLocaleCache2.get(locale);
  if (byIdCache == null) {
    iByLocaleCache2.put(locale,byIdCache=createCache());
  }
  Map<Boolean,Object> byNameKeyCache=byIdCache.get(id);
  if (byNameKeyCache == null) {
    byIdCache.put(id,byNameKeyCache=createCache());
    String[][] zoneStringsEn=DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
    String[] setEn=null;
    for (    String[] strings : zoneStringsEn) {
      if (strings != null && strings.length >= 5 && id.equals(strings[0])) {
        setEn=strings;
        break;
      }
    }
    String[][] zoneStringsLoc=DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
    String[] setLoc=null;
    for (    String[] strings : zoneStringsLoc) {
      if (strings != null && strings.length >= 5 && id.equals(strings[0])) {
        setLoc=strings;
        break;
      }
    }
    if (setEn != null && setLoc != null) {
      byNameKeyCache.put(Boolean.TRUE,new String[]{setLoc[2],setLoc[1]});
      byNameKeyCache.put(Boolean.FALSE,new String[]{setLoc[4],setLoc[3]});
    }
  }
  return (String[])byNameKeyCache.get(Boolean.valueOf(standardTime));
}
