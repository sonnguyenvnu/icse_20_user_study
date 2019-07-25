/** 
 * Creates a CodeFormat with the given message.
 * @param texts The text
 * @return The Component
 */
public static TextComponent wrap(String... texts){
  CodeFormat code=new CodeFormat();
  for (  String text : texts) {
    code.append(text);
  }
  return code.create();
}
