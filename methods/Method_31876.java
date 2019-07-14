/** 
 * Get the millis of second field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField millisOfSecond(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfSecond(),millis());
}
