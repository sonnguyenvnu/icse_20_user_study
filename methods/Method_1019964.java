/** 
 * If this value is present and satisfies <code>predicate</code>, return <code>just</code> the value; otherwise, return <code>nothing</code>.
 * @param predicate the predicate to apply to the possibly absent value
 * @return maybe the present value that satisfied the predicate
 */
public final Maybe<A> filter(Fn1<? super A,? extends Boolean> predicate){
  return flatMap(a -> predicate.apply(a) ? just(a) : nothing());
}
