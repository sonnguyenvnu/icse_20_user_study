/** 
 * modify polymorphically the target of a  {@link PTraversal} with a function 
 */
public final F<S,T> modify(final F<A,B> f){
  return s -> this.modifyP1F(a -> P.p(f.f(a))).f(s)._1();
}
