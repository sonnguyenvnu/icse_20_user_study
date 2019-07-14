/** 
 * Get the AM(0) PM(1) field for this chronology.
 * @return DateTimeField or UnsupportedDateTimeField if unsupported
 */
public DateTimeField halfdayOfDay(){
  return UnsupportedDateTimeField.getInstance(DateTimeFieldType.halfdayOfDay(),halfdays());
}
