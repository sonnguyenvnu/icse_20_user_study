/** 
 * Creates a duplicate of this TranslatableComponent.
 * @return the duplicate of this TranslatableComponent.
 */
@Override public BaseComponent duplicate(){
  return new TranslatableComponent(this);
}
