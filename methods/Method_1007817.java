/** 
 * Adds a string as a child to this Producer.
 * @param string The text
 * @return The producer, for chaining
 */
public TextComponentProducer append(String string){
  getBuilder().append(TextComponent.of(string));
  return this;
}
