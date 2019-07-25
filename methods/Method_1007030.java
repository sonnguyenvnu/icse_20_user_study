/** 
 * Transforms this actor to an actor on promises.
 * @return A new actor, equivalent to this actor, that acts on promises.
 */
public Actor<Promise<A>> promise(){
  return actor(s,(  Promise<A> b) -> b.to(Actor.this));
}
