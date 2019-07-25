/** 
 * First-class bifunctor map.
 * @return A first-class bifunctor map.
 */
public <B,J>F<F<I,J>,F<F<A,B>,Result<J,B>>> bimap(){
  return curry(this::bimap);
}
