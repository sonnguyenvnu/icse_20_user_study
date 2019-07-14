/** 
 * Removes a converter from the set of converters. If the converter was not in the set, no changes are made.
 * @param converter  the converter to remove, null ignored
 * @return replaced converter, or null
 */
public InstantConverter removeInstantConverter(InstantConverter converter) throws SecurityException {
  checkAlterInstantConverters();
  if (converter == null) {
    return null;
  }
  InstantConverter[] removed=new InstantConverter[1];
  iInstantConverters=iInstantConverters.remove(converter,removed);
  return removed[0];
}
