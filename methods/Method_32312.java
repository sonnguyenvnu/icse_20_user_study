/** 
 * Gets the formatter for the specified style.
 * @param dateStyle  the date style
 * @param timeStyle  the time style
 * @return the formatter
 */
private static DateTimeFormatter createFormatterForStyleIndex(int dateStyle,int timeStyle){
  int index=((dateStyle << 2) + dateStyle) + timeStyle;
  if (index >= cStyleCache.length()) {
    return createDateTimeFormatter(dateStyle,timeStyle);
  }
  DateTimeFormatter f=cStyleCache.get(index);
  if (f == null) {
    f=createDateTimeFormatter(dateStyle,timeStyle);
    if (cStyleCache.compareAndSet(index,null,f) == false) {
      f=cStyleCache.get(index);
    }
  }
  return f;
}
