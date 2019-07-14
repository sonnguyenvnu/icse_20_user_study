/** 
 * Get the millis of day field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField millisOfDay(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfDay(),millis());
}
