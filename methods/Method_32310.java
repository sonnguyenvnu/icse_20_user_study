/** 
 * Parses the given pattern and appends the rules to the given DateTimeFormatterBuilder.
 * @param pattern  pattern specification
 * @throws IllegalArgumentException if the pattern is invalid
 */
static void appendPatternTo(DateTimeFormatterBuilder builder,String pattern){
  parsePatternTo(builder,pattern);
}
