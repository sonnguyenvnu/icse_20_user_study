/** 
 * Returns a copy of this date with the partial set of fields replacing those from this instance. <p> For example, if the partial contains a year and a month then those two fields will be changed in the returned instance. Unsupported fields are ignored. If the partial is null, then <code>this</code> is returned.
 * @param partial  the partial set of fields to apply to this date, null ignored
 * @return a copy of this date with a different set of fields
 * @throws IllegalArgumentException if any value is invalid
 */
public LocalDate withFields(ReadablePartial partial){
  if (partial == null) {
    return this;
  }
  return withLocalMillis(getChronology().set(partial,getLocalMillis()));
}
