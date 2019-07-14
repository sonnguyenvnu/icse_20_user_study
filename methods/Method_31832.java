/** 
 * Gets the field type at the specified index.
 * @param index  the index to retrieve
 * @return the field at the specified index
 * @throws IndexOutOfBoundsException if the index is invalid
 * @since 2.0 (previously on BasePeriod)
 */
public DurationFieldType getFieldType(int index){
  return getPeriodType().getFieldType(index);
}
