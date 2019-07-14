/** 
 * Returns  {@code true} if this name node is the {@code attr} child(i.e. the attribute being accessed) of an  {@link Attribute} node.
 */
public boolean isAttribute(){
  return parent instanceof Attribute && ((Attribute)parent).getAttr() == this;
}
