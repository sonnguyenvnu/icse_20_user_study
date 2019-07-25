/** 
 * Run the stateful computation, returning the final state.
 * @param s the initial state
 * @return the final state
 */
public S exec(S s){
  return run(s)._2();
}
