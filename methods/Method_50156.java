/** 
 * Returns the GraphQL name of the supplied proto. 
 */
static String getReferenceName(GenericDescriptor descriptor){
  return CharMatcher.anyOf(".").replaceFrom(descriptor.getFullName(),"_");
}
