/** 
 * Requires the number to be inside a range.
 * @param < N > Type of number
 * @return A range constraint
 */
public static <N extends Number & Comparable<N>>PropertyConstraint<N> inRange(final N minInclusive,final N maxInclusive){
  return ConstraintFactory.fromPredicate(new Predicate<N>(){
    @Override public boolean test(    N t){
      return minInclusive.compareTo(t) <= 0 && maxInclusive.compareTo(t) >= 0;
    }
  }
,"Should be between " + minInclusive + " and " + maxInclusive);
}
