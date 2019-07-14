/** 
 * Returns a value parser parsing lists of values of type U.
 * @param parser    Parser used to parse a single value
 * @param delimiter Char delimiting values
 * @param < U >       Element type of the target list
 * @return A list of values
 */
public static <U>ValueParser<List<U>> multi(final ValueParser<U> parser,final char delimiter){
  return new ValueParser<List<U>>(){
    @Override public List<U> valueOf(    String value) throws IllegalArgumentException {
      return parsePrimitives(value,delimiter,parser);
    }
  }
;
}
