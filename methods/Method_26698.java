/** 
 * Returns a  {@code Unifier} containing all the bindings from this {@code Unifier}, but which can succeed or fail independently of this  {@code Unifier}.
 */
public Unifier fork(){
  return new Unifier(context,bindings);
}
