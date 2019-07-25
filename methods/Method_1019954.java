/** 
 * Given a value of type <code>A</code>, produced an instance of this tuple with each slot set to that value.
 * @param a   the value to fill the tuple with
 * @param < A > the value type
 * @return the filled tuple
 * @see Tuple2#fill
 */
public static <A>Tuple7<A,A,A,A,A,A,A> fill(A a){
  return tuple(a,a,a,a,a,a,a);
}
