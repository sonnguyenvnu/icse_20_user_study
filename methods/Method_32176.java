/** 
 * Compare this field to the same field on another instant. <p> The comparison is based on the value of the same field type, irrespective of any difference in chronology. Thus, if this property represents the hourOfDay field, then the hourOfDay field of the other instant will be queried whether in the same chronology or not.
 * @param instant  the instant to compare to
 * @return negative value if this is less, 0 if equal, or positive value if greater
 * @throws IllegalArgumentException if the instant is null or the instantdoesn't support the field of this property
 */
public int compareTo(ReadableInstant instant){
  if (instant == null) {
    throw new IllegalArgumentException("The instant must not be null");
  }
  int thisValue=get();
  int otherValue=instant.get(getFieldType());
  if (thisValue < otherValue) {
    return -1;
  }
 else   if (thisValue > otherValue) {
    return 1;
  }
 else {
    return 0;
  }
}
