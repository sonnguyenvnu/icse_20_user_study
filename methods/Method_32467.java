/** 
 * Get the date time as a <code>java.util.Date</code>. <p> The <code>Date</code> object created has exactly the same year, month and day as this date. The time will be set to the earliest valid time for that date. <p> Converting to a JDK Date is full of complications as the JDK Date constructor doesn't behave as you might expect around DST transitions. This method works by taking a first guess and then adjusting the JDK date until it has the earliest valid instant. This also handles the situation where the JDK time zone data differs from the Joda-Time time zone data.
 * @return a Date initialised with this date, never null
 * @since 2.0
 */
@SuppressWarnings("deprecation") public Date toDate(){
  int dom=getDayOfMonth();
  Date date=new Date(getYear() - 1900,getMonthOfYear() - 1,dom);
  LocalDate check=LocalDate.fromDateFields(date);
  if (check.isBefore(this)) {
    while (check.equals(this) == false) {
      date.setTime(date.getTime() + 3600000);
      check=LocalDate.fromDateFields(date);
    }
    while (date.getDate() == dom) {
      date.setTime(date.getTime() - 1000);
    }
    date.setTime(date.getTime() + 1000);
  }
 else   if (check.equals(this)) {
    Date earlier=new Date(date.getTime() - TimeZone.getDefault().getDSTSavings());
    if (earlier.getDate() == dom) {
      date=earlier;
    }
  }
  return date;
}
