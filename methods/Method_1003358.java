/** 
 * Get or create an enum value with the given label and ordinal.
 * @param label the label
 * @param ordinal the ordinal
 * @return the value
 */
public static ValueEnumBase get(final String label,final int ordinal){
  return new ValueEnumBase(label,ordinal);
}
