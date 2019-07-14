/** 
 * Parses a string into a list of values of type  {@literal <U>}.
 * @param toParse   The string to parse
 * @param delimiter The delimiter to use
 * @param extractor The function mapping a string to an instance of {@code <U>}
 * @param < U >       The type of the values to parse
 * @return A list of values
 */
static <U>List<U> parsePrimitives(String toParse,char delimiter,ValueParser<U> extractor){
  String[] values=StringUtils.split(toParse,delimiter);
  List<U> result=new ArrayList<>();
  for (  String s : values) {
    result.add(extractor.valueOf(s));
  }
  return result;
}
