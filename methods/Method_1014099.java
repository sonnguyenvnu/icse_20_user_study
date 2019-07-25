/** 
 * Converts all parameters to an expression array. Objects that are already instances of  {@link Expression} are nottouched. All others are converted to  {@link match} expressions.
 * @param obj the objects that are to be converted
 * @return resulting expression array
 */
protected Expression[] exps(Object... objects){
  ArrayList<Expression> result=new ArrayList<Expression>();
  for (int i=0; i < objects.length; i++) {
    Expression e=exp(objects[i]);
    if (e != null) {
      result.add(e);
    }
  }
  return result.toArray(new Expression[0]);
}
