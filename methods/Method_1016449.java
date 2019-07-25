/** 
 * get all dates in the text
 * @param text
 * @return a set of dates, ordered by time. first date in the ordered set is the oldest time.
 */
public static LinkedHashSet<Date> parse(String text,int timezoneOffset){
  LinkedHashSet<Date> dates=parseRawDate(text);
  for (  Map.Entry<Pattern,Date[]> entry : HolidayPattern.entrySet()) {
    if (entry.getKey().matcher(text).find()) {
      for (      Date d : entry.getValue())       dates.add(d);
    }
  }
  return dates;
}
