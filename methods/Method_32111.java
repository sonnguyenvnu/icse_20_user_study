/** 
 * Converts this object to a <code>DateMidnight</code> using the same millis and chronology.
 * @return a DateMidnight using the same millis and chronology
 * @deprecated DateMidnight is deprecated
 */
@Deprecated public DateMidnight toDateMidnight(){
  return new DateMidnight(getMillis(),getChronology());
}
