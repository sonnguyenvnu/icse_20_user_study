/** 
 * Parse the given pattern expression.
 */
private void parse(String expression) throws IllegalArgumentException {
  String[] fields=Strings.splitIgnoreBlank(expression," ");
  if (fields.length != 6) {
    throw new IllegalArgumentException(String.format("Cron expression must consist of 6 fields (found %d in \"%s\")",fields.length,expression));
  }
  setNumberHits(this.seconds,fields[0],0,60);
  setNumberHits(this.minutes,fields[1],0,60);
  setNumberHits(this.hours,fields[2],0,24);
  setDaysOfMonth(this.daysOfMonth,fields[3]);
  setMonths(this.months,fields[4]);
  setDays(this.daysOfWeek,replaceOrdinals(fields[5],"SUN,MON,TUE,WED,THU,FRI,SAT"),8);
  if (this.daysOfWeek.get(7)) {
    this.daysOfWeek.set(0);
    this.daysOfWeek.clear(7);
  }
}
