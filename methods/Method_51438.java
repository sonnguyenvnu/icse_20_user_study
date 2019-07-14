/** 
 * Returns a builder for a character property. The property descriptor will accept any single character string. No unescaping is performed other than what the XML parser does itself. That means that Java escape sequences are not expanded: e.g. "\n", will be represented as the character sequence '\' 'n', so it's not a valid value for this type of property. On the other hand, XML character references are expanded, like  {@literal &amp;} ('&amp;') or {@literal &lt;} ('&lt;').
 * @param name Name of the property to build
 * @return A new builder
 */
public static GenericPropertyBuilder<Character> charProperty(String name){
  return new GenericPropertyBuilder<>(name,ValueParserConstants.CHARACTER_PARSER,Character.class);
}
