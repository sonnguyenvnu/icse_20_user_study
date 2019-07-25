/** 
 * Creates a duplicate of this TextComponent.
 * @return the duplicate of this TextComponent.
 */
@Override public BaseComponent duplicate(){
  return new TextComponent(this);
}
