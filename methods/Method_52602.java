/** 
 * @return the intersection of the two types
 */
public static JavaTypeDefinition intersect(JavaTypeDefinition first,JavaTypeDefinition second){
  if (first.equals(second)) {
    return first;
  }
 else   if (first.getType() == second.getType()) {
    if (!first.isRawType() && !second.isRawType()) {
      return merge(first,second);
    }
 else {
      return JavaTypeDefinition.forClass(first.getType());
    }
  }
  throw new ResolutionFailedException();
}
