/** 
 * Return the type for  {@code qname}, or  {@code null} if not known.
 * @throws IllegalStateException if {@link #ready} has not been called.
 */
public Type lookupFirstBindingType(String qname){
  Binding b=lookupFirstBinding(qname);
  if (b != null) {
    return b.getType();
  }
  return null;
}
