/** 
 * Removes a converter from the set of converters. If the converter was not in the set, no changes are made.
 * @param converter  the converter to remove, null ignored
 * @return replaced converter, or null
 */
public DurationConverter removeDurationConverter(DurationConverter converter) throws SecurityException {
  checkAlterDurationConverters();
  if (converter == null) {
    return null;
  }
  DurationConverter[] removed=new DurationConverter[1];
  iDurationConverters=iDurationConverters.remove(converter,removed);
  return removed[0];
}
