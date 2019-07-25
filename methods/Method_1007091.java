/** 
 * Returns the list of initial segments of this list, shortest first.
 * @return The list of initial segments of this list, shortest first.
 */
public final List<List<A>> inits(){
  List<List<A>> s=single(List.nil());
  if (isNotEmpty())   s=s.append(tail().inits().map(List.<A>cons().f(head())));
  return s;
}
