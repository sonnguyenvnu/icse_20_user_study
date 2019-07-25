/** 
 * find the first target of a  {@link Fold} matching the predicate 
 */
public final F<S,Option<A>> find(final F<A,Boolean> p){
  return foldMap(Monoid.firstOptionMonoid(),a -> p.f(a) ? Option.some(a) : Option.none());
}
