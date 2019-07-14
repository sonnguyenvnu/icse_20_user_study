/** 
 * Returns a predicate that represents the logical negation of this predicate.
 * @return a predicate that represents the logical negation of thispredicate
 */
default ThrowingPredicate<T> negate(){
  return (t) -> !testThrows(t);
}
