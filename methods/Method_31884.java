/** 
 * Get the year of a week based year in a century field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField weekyearOfCentury(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyearOfCentury(),weekyears());
}
