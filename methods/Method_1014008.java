/** 
 * Formats the value of this type according to a pattern (see  {@link Formatter}). One single value of this type can be referenced by the pattern using an index. The item order is defined by the natural (alphabetical) order of their keys.
 * @param pattern the pattern to use containing indexes to reference the single elements of this type
 * @return the formatted string
 */
@Override public String format(@Nullable String pattern){
  String formatPattern=pattern;
  if (formatPattern == null || "%s".equals(formatPattern)) {
    formatPattern=LOCATION_PATTERN;
  }
  return String.format(formatPattern,getConstituents().values().toArray());
}
