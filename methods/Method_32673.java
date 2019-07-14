/** 
 * Returns a copy of this time with the specified chronology. This instance is immutable and unaffected by this method call. <p> This method retains the values of the fields, thus the result will typically refer to a different instant. <p> The time zone of the specified chronology is ignored, as TimeOfDay operates without a time zone.
 * @param newChronology  the new chronology, null means ISO
 * @return a copy of this datetime with a different chronology
 * @throws IllegalArgumentException if the values are invalid for the new chronology
 */
public TimeOfDay withChronologyRetainFields(Chronology newChronology){
  newChronology=DateTimeUtils.getChronology(newChronology);
  newChronology=newChronology.withUTC();
  if (newChronology == getChronology()) {
    return this;
  }
 else {
    TimeOfDay newTimeOfDay=new TimeOfDay(this,newChronology);
    newChronology.validate(newTimeOfDay,getValues());
    return newTimeOfDay;
  }
}
