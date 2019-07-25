/** 
 * {@inheritDoc}
 */
@Override default <CoP extends Profunctor<?,?,? extends Cocartesian<?,?,?>>,CoF extends Functor<?,? extends Identity<?>>,FB extends Functor<B,? extends CoF>,FT extends Functor<T,? extends CoF>,PAFB extends Profunctor<A,FB,? extends CoP>,PSFT extends Profunctor<S,FT,? extends CoP>>PSFT apply(PAFB pafb){
  @SuppressWarnings("RedundantTypeArguments") Optic<Cocartesian<?,?,?>,Identity<?>,S,T,A,B> optic=this.<Identity<?>>toOptic(Identity::new);
  return optic.apply(pafb);
}
