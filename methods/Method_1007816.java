/** 
 * Creates a SubtleFormat with the given message.
 * @param texts The text
 * @return The Component
 */
public static TextComponent wrap(String... texts){
  SubtleFormat subtle=new SubtleFormat();
  for (  String component : texts) {
    subtle.append(component);
  }
  return subtle.create();
}
