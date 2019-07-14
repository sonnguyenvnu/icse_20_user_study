/** 
 * Get the year of century field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField yearOfCentury(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfCentury(),years());
}
