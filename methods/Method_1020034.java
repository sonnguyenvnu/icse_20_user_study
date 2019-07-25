/** 
 * Static factory method to create a predicate from an  {@link Fn1}.
 * @param predicate the {@link Fn1}
 * @param < A >       the input type
 * @return the predicate
 */
static <A>Predicate<A> predicate(Fn1<? super A,? extends Boolean> predicate){
  return predicate::apply;
}
