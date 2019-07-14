/** 
 * Extracts the values of the partial from an object of this converter's type. The chrono parameter is a hint to the converter, should it require a chronology to aid in conversion. <p> This implementation calls  {@link #getInstantMillis(Object,Chronology)}.
 * @param fieldSource  a partial that provides access to the fields.This partial may be incomplete and only getFieldType(int) should be used
 * @param object  the object to convert
 * @param chrono  the chronology to use, which is the non-null result of getChronology()
 * @return the array of field values that match the fieldSource, must be non-null valid
 * @throws ClassCastException if the object is invalid
 */
public int[] getPartialValues(ReadablePartial fieldSource,Object object,Chronology chrono){
  long instant=getInstantMillis(object,chrono);
  return chrono.get(fieldSource,instant);
}
