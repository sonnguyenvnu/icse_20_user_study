/** 
 * Returns a newly-created  {@link org.inferred.freebuilder.processor.property.Property} based onthe contents of this  {@code Builder}.
 * @throws IllegalStateException if any field has not been set
 */
public org.inferred.freebuilder.processor.property.Property build(){
  Preconditions.checkState(_unsetProperties.isEmpty(),"Not set: %s",_unsetProperties);
  return new Value(this);
}
