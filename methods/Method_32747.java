/** 
 * Returns a copy of this year-month with the specified chronology. This instance is immutable and unaffected by this method call. <p> This method retains the values of the fields, thus the result will typically refer to a different instant. <p> The time zone of the specified chronology is ignored, as YearMonth operates without a time zone.
 * @param newChronology  the new chronology, null means ISO
 * @return a copy of this year-month with a different chronology, never null
 * @throws IllegalArgumentException if the values are invalid for the new chronology
 */
public YearMonth withChronologyRetainFields(Chronology newChronology){
  newChronology=DateTimeUtils.getChronology(newChronology);
  newChronology=newChronology.withUTC();
  if (newChronology == getChronology()) {
    return this;
  }
 else {
    YearMonth newYearMonth=new YearMonth(this,newChronology);
    newChronology.validate(newYearMonth,getValues());
    return newYearMonth;
  }
}
