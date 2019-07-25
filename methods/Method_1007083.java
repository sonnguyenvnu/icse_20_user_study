/** 
 * Joins the given stream of lazy strings into one, separated by newlines.
 * @param str A stream of lazy strings to join by newlines.
 * @return A new lazy string, consisting of the given strings separated by newlines.
 */
public static LazyString unlines(final Stream<LazyString> str){
  return fromStream(join(str.intersperse(str("\n")).map(toStream)));
}
