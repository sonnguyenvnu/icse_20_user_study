/** 
 * {@inheritDoc}
 */
@Override default <CoP extends Profunctor<?,?,? extends P>,CoF extends Functor<?,? extends Identity<?>>,FB extends Functor<B,? extends CoF>,FT extends Functor<T,? extends CoF>,PAFB extends Profunctor<A,FB,? extends CoP>,PSFT extends Profunctor<S,FT,? extends CoP>>PSFT apply(PAFB pafb){
  return toOptic(Pure.<Identity<?>>pure(Identity::new)).apply(pafb);
}
