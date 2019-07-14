/** 
 * Get the hour of am/pm (offset to 1-12) field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField clockhourOfHalfday(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfHalfday(),hours());
}
