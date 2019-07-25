/** 
 * Concatenates identifiers into a '.' delimited string.
 * @param identifiers provides the ordered list of identifiers to join.
 * @return a string.
 */
public static String join(List<PdlParser.IdentifierContext> identifiers){
  StringBuilder stringBuilder=new StringBuilder();
  Iterator<PdlParser.IdentifierContext> iter=identifiers.iterator();
  while (iter.hasNext()) {
    stringBuilder.append(iter.next().value);
    if (iter.hasNext()) {
      stringBuilder.append(".");
    }
  }
  return stringBuilder.toString();
}
