/** 
 * Python's list comprehension will bind the variables used in generators. This will erase the original values of the variables even after the comprehension.
 */
@NotNull @Override public Type transform(State s){
  resolveList(generators,s);
  return new ListType(transformExpr(elt,s));
}
