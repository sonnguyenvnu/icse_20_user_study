/** 
 * Get the hour of day (offset to 1-24) field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField clockhourOfDay(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfDay(),hours());
}
