/** 
 * Add the specified month to the specified time instant. The amount added may be negative.<p> If the new month has less total days than the specified day of the month, this value is coerced to the nearest sane value. e.g.<p> 07-31 - (1 month) = 06-30<p> 03-31 - (1 month) = 02-28 or 02-29 depending<p>
 * @see org.joda.time.DateTimeField#add
 * @see org.joda.time.ReadWritableDateTime#addMonths(int)
 * @param instant  the time instant in millis to update.
 * @param months  the months to add (can be negative).
 * @return the updated time instant.
 */
public long add(long instant,int months){
  if (months == 0) {
    return instant;
  }
  long timePart=iChronology.getMillisOfDay(instant);
  int thisYear=iChronology.getYear(instant);
  int thisMonth=iChronology.getMonthOfYear(instant,thisYear);
  int yearToUse=thisYear;
  ;
  int monthToUse=thisMonth - 1 + months;
  if (thisMonth > 0 && monthToUse < 0) {
    if (Math.signum(months + iMax) == Math.signum(months)) {
      yearToUse--;
      months+=iMax;
    }
 else {
      yearToUse++;
      months-=iMax;
    }
    monthToUse=thisMonth - 1 + months;
  }
  if (monthToUse >= 0) {
    yearToUse=yearToUse + (monthToUse / iMax);
    monthToUse=(monthToUse % iMax) + 1;
  }
 else {
    yearToUse=yearToUse + (monthToUse / iMax) - 1;
    monthToUse=Math.abs(monthToUse);
    int remMonthToUse=monthToUse % iMax;
    if (remMonthToUse == 0) {
      remMonthToUse=iMax;
    }
    monthToUse=iMax - remMonthToUse + 1;
    if (monthToUse == 1) {
      yearToUse+=1;
    }
  }
  int dayToUse=iChronology.getDayOfMonth(instant,thisYear,thisMonth);
  int maxDay=iChronology.getDaysInYearMonth(yearToUse,monthToUse);
  if (dayToUse > maxDay) {
    dayToUse=maxDay;
  }
  long datePart=iChronology.getYearMonthDayMillis(yearToUse,monthToUse,dayToUse);
  return datePart + timePart;
}
