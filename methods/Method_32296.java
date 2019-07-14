/** 
 * Factory to create a format from a two character style pattern. <p> The first character is the date style, and the second character is the time style. Specify a character of 'S' for short style, 'M' for medium, 'L' for long, and 'F' for full. A date or time may be omitted by specifying a style character '-'. <p> The returned formatter will dynamically adjust to the locale that the print/parse takes place in. Thus you just call {@link DateTimeFormatter#withLocale(Locale)} and the Short/Medium/Long/Fullstyle for that locale will be output. For example: <pre> DateTimeFormat.forStyle(style).withLocale(Locale.FRANCE).print(dt); </pre>
 * @param style  two characters from the set {"S", "M", "L", "F", "-"}
 * @return the formatter
 * @throws IllegalArgumentException if the style is invalid
 */
public static DateTimeFormatter forStyle(String style){
  return createFormatterForStyle(style);
}
