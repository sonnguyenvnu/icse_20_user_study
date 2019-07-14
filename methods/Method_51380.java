/** 
 * Requires the number to be strictly positive. The int values of the number is used for comparison so there may be some unexpected behaviour with decimal numbers.
 * @param < N > Type of number
 * @return A positivity constraint
 */
public static <N extends Number>PropertyConstraint<N> positive(){
  return ConstraintFactory.fromPredicate(new Predicate<N>(){
    @Override public boolean test(    N t){
      return t.intValue() > 0;
    }
  }
,"Should be positive");
}
