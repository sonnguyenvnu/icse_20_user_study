/** 
 * Returns a newly-created  {@link TypeUsage} based on the contents of this {@code Builder}.
 * @throws IllegalStateException if any field has not been set
 */
public TypeUsage build(){
  Preconditions.checkState(_unsetProperties.isEmpty(),"Not set: %s",_unsetProperties);
  return new Value(this);
}
