/** 
 * Wrap an  {@link Iterable} in a {@link LambdaIterable}.
 * @param as  the Iterable
 * @param < A > the Iterable element type
 * @return the Iterable wrapped in a {@link LambdaIterable}
 */
public static <A>LambdaIterable<A> wrap(Iterable<? extends A> as){
  return new LambdaIterable<>(as);
}
