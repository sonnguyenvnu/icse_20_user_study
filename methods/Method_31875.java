/** 
 * Sets the partial into the instant.
 * @param partial  the partial instant to use
 * @param instant  the instant to update
 * @return the updated instant
 */
public long set(ReadablePartial partial,long instant){
  for (int i=0, isize=partial.size(); i < isize; i++) {
    instant=partial.getFieldType(i).getField(this).set(instant,partial.getValue(i));
  }
  return instant;
}
