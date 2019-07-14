/** 
 * Finds a GraphQL type by it's reference name. 
 */
public static ModifiableType find(String typeReferenceName){
  return new ModifiableType(typeReferenceName);
}
