/** 
 * formats a value for its usage in XPath expressions
 * @param attribute atribute which value should be formatted
 * @return formmated value
 */
public static String formatValueForXPath(Attribute attribute){
  return '\'' + attribute.getStringValue() + '\'';
}
