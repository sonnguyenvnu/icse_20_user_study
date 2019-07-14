/** 
 * Get the day of year field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField dayOfYear(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfYear(),days());
}
