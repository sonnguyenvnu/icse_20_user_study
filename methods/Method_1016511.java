/** 
 * Sets the value to be returned by  {@link TypeUsage#start()}.
 * @return this {@code Builder} object
 */
public TypeUsage.Builder start(int start){
  this.start=start;
  _unsetProperties.remove(Property.START);
  return (TypeUsage.Builder)this;
}
