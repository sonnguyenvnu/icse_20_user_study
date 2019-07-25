/** 
 * Converts an object to an expression. Objects that are already instances of  {@link Expression} are just returned.All others are converted to  {@link match} expressions.
 * @param obj the object that's to be converted
 * @return resulting expression
 */
protected Expression exp(Object obj){
  if (obj instanceof Expression) {
    return (Expression)obj;
  }
 else {
    return obj == null ? null : new ExpressionMatch(obj.toString());
  }
}
