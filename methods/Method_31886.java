/** 
 * Get the year of era field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField yearOfEra(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfEra(),years());
}
