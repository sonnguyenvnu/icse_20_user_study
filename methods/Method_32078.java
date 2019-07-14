/** 
 * Extracts the values of the partial from an object of this converter's type. This method checks if the parser has a zone, and uses it if present. This is most useful for parsing local times with UTC.
 * @param fieldSource  a partial that provides access to the fields.This partial may be incomplete and only getFieldType(int) should be used
 * @param object  the object to convert
 * @param chrono  the chronology to use, which is the non-null result of getChronology()
 * @param parser the parser to use, may be null
 * @return the array of field values that match the fieldSource, must be non-null valid
 * @throws ClassCastException if the object is invalid
 * @throws IllegalArgumentException if the value if invalid
 * @since 1.3
 */
public int[] getPartialValues(ReadablePartial fieldSource,Object object,Chronology chrono,DateTimeFormatter parser){
  if (parser.getZone() != null) {
    chrono=chrono.withZone(parser.getZone());
  }
  long millis=parser.withChronology(chrono).parseMillis((String)object);
  return chrono.get(fieldSource,millis);
}
