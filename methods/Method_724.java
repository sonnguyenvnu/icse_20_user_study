/** 
 * Wrap the body in a  {@link MappingFastJsonValue} value container (for providingadditional serialization instructions) or simply cast it if already wrapped.
 */
protected MappingFastJsonValue getOrCreateContainer(Object body){
  return (body instanceof MappingFastJsonValue ? (MappingFastJsonValue)body : new MappingFastJsonValue(body));
}
