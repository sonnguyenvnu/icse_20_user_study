/** 
 * Shortcut to append a line of generated code
 * @param builder string builder to which to append the line
 * @param indent  current text indentation
 * @param line    line to be appended
 */
public static void append(final StringBuilder builder,final String indent,final String line){
  builder.append(indent).append(line).append('\n');
}
