/** 
 * Excludes type names. You can disable serialization of properties that are of some type. For example, you can disable properties of <code>InputStream</code>. You can use wildcards to describe type names.
 */
public JsonSerializer excludeTypes(final String... typeNames){
  if (excludedTypeNames == null) {
    excludedTypeNames=typeNames;
  }
 else {
    excludedTypeNames=ArraysUtil.join(excludedTypeNames,typeNames);
  }
  return this;
}
