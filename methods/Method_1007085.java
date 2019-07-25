/** 
 * Joins the given stream of lazy strings into one, separated by spaces.
 * @param str A stream of lazy strings to join by spaces.
 * @return A new lazy string, consisting of the given strings with spaces in between.
 */
public static LazyString unwords(final Stream<LazyString> str){
  return fromStream(join(str.intersperse(str(" ")).map(toStream)));
}
