/** 
 * Given a value of type <code>A</code>, produce an instance of this tuple with each slot set to that value.
 * @param a   the value to fill the tuple with
 * @param < A > the value type
 * @return the filled tuple
 */
public static <A>Tuple2<A,A> fill(A a){
  return tuple(a,a);
}
