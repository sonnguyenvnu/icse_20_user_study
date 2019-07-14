/** 
 * Sets the type for the node.
 * @param newType the new type
 * @return {@code newType}
 * @throws IllegalArgumentException if {@code newType} is {@code null}
 */
public Type setType(Type newType){
  if (newType == null) {
    throw new IllegalArgumentException();
  }
  this.type=newType;
  return this.type;
}
