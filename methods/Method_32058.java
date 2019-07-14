/** 
 * Adds a converter to the set of converters. If a matching converter is already in the set, the given converter replaces it. If the converter is exactly the same as one already in the set, no changes are made. <p> The order in which converters are added is not relevant. The best converter is selected by examining the object hierarchy.
 * @param converter  the converter to add, null ignored
 * @return replaced converter, or null
 */
public PeriodConverter addPeriodConverter(PeriodConverter converter) throws SecurityException {
  checkAlterPeriodConverters();
  if (converter == null) {
    return null;
  }
  PeriodConverter[] removed=new PeriodConverter[1];
  iPeriodConverters=iPeriodConverters.add(converter,removed);
  return removed[0];
}
