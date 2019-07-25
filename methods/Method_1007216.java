/** 
 * Swaps the arguments when summing.
 */
public Semigroup<A> dual(){
  return semigroupDef(def.dual());
}
