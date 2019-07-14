/** 
 * Construct a Strategy that parses a Text field
 * @param field            The Calendar field
 * @param definingCalendar The calendar to obtain the short and long values
 * @return a TextStrategy for the field and Locale
 */
private Strategy getLocaleSpecificStrategy(final int field,final Calendar definingCalendar){
  final ConcurrentMap<Locale,Strategy> cache=getCache(field);
  Strategy strategy=cache.get(locale);
  if (strategy == null) {
    strategy=field == Calendar.ZONE_OFFSET ? new TimeZoneStrategy(locale) : new TextStrategy(field,definingCalendar,locale);
    final Strategy inCache=cache.putIfAbsent(locale,strategy);
    if (inCache != null) {
      return inCache;
    }
  }
  return strategy;
}
