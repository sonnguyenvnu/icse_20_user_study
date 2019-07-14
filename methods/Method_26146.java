/** 
 * Classifies bad casts. 
 */
private static String identifyBadCast(Type lhs,Type rhs,Types types){
  if (!lhs.isPrimitive()) {
    return null;
  }
  if (types.isConvertible(rhs,lhs)) {
    return null;
  }
  return String.format("Compound assignments from %s to %s hide lossy casts",prettyType(rhs),prettyType(lhs));
}
