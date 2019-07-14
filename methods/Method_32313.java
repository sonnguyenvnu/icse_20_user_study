/** 
 * Creates a formatter for the specified style.
 * @param dateStyle  the date style
 * @param timeStyle  the time style
 * @return the formatter
 */
private static DateTimeFormatter createDateTimeFormatter(int dateStyle,int timeStyle){
  int type=DATETIME;
  if (dateStyle == NONE) {
    type=TIME;
  }
 else   if (timeStyle == NONE) {
    type=DATE;
  }
  StyleFormatter llf=new StyleFormatter(dateStyle,timeStyle,type);
  return new DateTimeFormatter(llf,llf);
}
