/** 
 * invoked [0..n] times
 * @deprecated use {@link #property(String,long)} instead.
 */
@Deprecated @ToRemove(version=2018.3) public ConceptDescriptorBuilder2 prop(String name,long propertyId,SNodeReference srcNode){
  myProperties.add(new BasePropertyDescriptor(MetaIdFactory.propId(myConceptId,propertyId),name,null,srcNode));
  return this;
}
