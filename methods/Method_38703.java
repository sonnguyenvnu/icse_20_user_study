/** 
 * Excludes types. Supports interfaces and subclasses as well.
 */
public JsonSerializer excludeTypes(final Class... types){
  if (excludedTypes == null) {
    excludedTypes=types;
  }
 else {
    excludedTypes=ArraysUtil.join(excludedTypes,types);
  }
  return this;
}
