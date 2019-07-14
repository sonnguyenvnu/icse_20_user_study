/** 
 * Adds a converter to the set of converters. If a matching converter is already in the set, the given converter replaces it. If the converter is exactly the same as one already in the set, no changes are made. <p> The order in which converters are added is not relevant. The best converter is selected by examining the object hierarchy.
 * @param converter  the converter to add, null ignored
 * @return replaced converter, or null
 */
public DurationConverter addDurationConverter(DurationConverter converter) throws SecurityException {
  checkAlterDurationConverters();
  if (converter == null) {
    return null;
  }
  DurationConverter[] removed=new DurationConverter[1];
  iDurationConverters=iDurationConverters.add(converter,removed);
  return removed[0];
}
