/** 
 * Finds the GraphQL type for a Proto descriptor. 
 */
public static ModifiableType find(GenericDescriptor typeDescriptor){
  return new ModifiableType(ProtoToGql.getReferenceName(typeDescriptor));
}
