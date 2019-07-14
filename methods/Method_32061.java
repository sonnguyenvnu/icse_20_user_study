/** 
 * Removes a converter from the set of converters. If the converter was not in the set, no changes are made.
 * @param converter  the converter to remove, null ignored
 * @return replaced converter, or null
 */
public IntervalConverter removeIntervalConverter(IntervalConverter converter) throws SecurityException {
  checkAlterIntervalConverters();
  if (converter == null) {
    return null;
  }
  IntervalConverter[] removed=new IntervalConverter[1];
  iIntervalConverters=iIntervalConverters.remove(converter,removed);
  return removed[0];
}
