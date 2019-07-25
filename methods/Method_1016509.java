/** 
 * Returns a newly-created  {@link Datatype} based on the contents of this {@code Builder}.
 * @throws IllegalStateException if any field has not been set
 */
public Datatype build(){
  Preconditions.checkState(_unsetProperties.isEmpty(),"Not set: %s",_unsetProperties);
  return new Value(this);
}
