/** 
 * Turns a List of promises into a single promise of a List.
 * @param s  The strategy with which to sequence the promises.
 * @param as The list of promises to transform.
 * @return A single promise for the given List.
 */
public static <A>Promise<List<A>> sequence(final Strategy<Unit> s,final List<Promise<A>> as){
  return join(foldRight(s,liftM2(List.<A>cons()),promise(s,p(List.<A>nil()))).f(as));
}
