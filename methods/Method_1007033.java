/** 
 * Binds the given function over this promise, with a final join. The bind function for the Promise monad.
 * @param f The function to bind over this promise.
 * @return The result of applying the given function to this promised value.
 */
public <B>Promise<B> bind(final F<A,Promise<B>> f){
  final Promise<B> r=mkPromise(s);
  final Actor<B> ab=actor(s,new Effect1<B>(){
    public void f(    final B b){
      r.actor.act(p(Either.left(p(b)),r));
    }
  }
);
  to(ab.promise().contramap(f));
  return r;
}
