/** 
 * Python's list comprehension will erase any variable used in generators. This is wrong, but we "respect" this bug here.
 */
@NotNull @Override public Type transform(State s){
  resolveList(generators,s);
  return new ListType(transformExpr(elt,s));
}
