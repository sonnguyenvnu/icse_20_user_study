/** 
 * Sets the value to be returned by  {@link TypeUsage#end()}.
 * @return this {@code Builder} object
 */
public TypeUsage.Builder end(int end){
  this.end=end;
  _unsetProperties.remove(Property.END);
  return (TypeUsage.Builder)this;
}
