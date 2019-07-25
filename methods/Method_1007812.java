/** 
 * Creates a LabelFormat with the given message.
 * @param texts The text
 * @return The Component
 */
public static TextComponent wrap(String... texts){
  LabelFormat label=new LabelFormat();
  for (  String component : texts) {
    label.append(component);
  }
  return label.create();
}
