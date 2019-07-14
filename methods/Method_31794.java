/** 
 * Converts this duration to a Period instance using the standard period type and the ISO chronology. <p> Only precise fields in the period type will be used. Thus, only the hour, minute, second and millisecond fields on the period will be used. The year, month, week and day fields will not be populated. <p> If the duration is small, less than one day, then this method will perform as you might expect and split the fields evenly. If the duration is larger than one day then all the remaining duration will be stored in the largest available field, hours in this case. <p> For example, a duration effectively equal to (365 + 60 + 5) days will be converted to ((365 + 60 + 5) * 24) hours by this constructor. <p> For more control over the conversion process, you must pair the duration with an instant, see  {@link Period#Period(ReadableInstant,ReadableDuration)}.
 * @return a Period created using the millisecond duration from this instance
 */
public Period toPeriod(){
  return new Period(getMillis());
}
