/** 
 * <p>Gets the time zone display name, using a cache for performance.</p>
 * @param tz       the zone to query
 * @param daylight true if daylight savings
 * @param style    the style to use {@code TimeZone.LONG} or {@code TimeZone.SHORT}
 * @param locale   the locale to use
 * @return the textual name of the time zone
 */
static String getTimeZoneDisplay(final TimeZone tz,final boolean daylight,final int style,final Locale locale){
  final TimeZoneDisplayKey key=new TimeZoneDisplayKey(tz,daylight,style,locale);
  String value=cTimeZoneDisplayCache.get(key);
  if (value == null) {
    value=tz.getDisplayName(daylight,style,locale);
    final String prior=cTimeZoneDisplayCache.putIfAbsent(key,value);
    if (prior != null) {
      value=prior;
    }
  }
  return value;
}
