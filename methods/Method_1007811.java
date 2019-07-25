/** 
 * Creates an ErrorFormat with the given message.
 * @param texts The text
 * @return The Component
 */
public static TextComponent wrap(String... texts){
  ErrorFormat error=new ErrorFormat();
  for (  String component : texts) {
    error.append(component);
  }
  return error.create();
}
