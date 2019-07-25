/** 
 * Reframe an  {@link Optic} according to covariant bounds.
 * @param optic the {@link Optic}
 * @param < P >   the {@link Profunctor} type
 * @param < F >   the {@link Functor} type
 * @param < S >   the left side of the output profunctor
 * @param < T >   the right side's functor embedding of the output profunctor
 * @param < A >   the left side of the input profunctor
 * @param < B >   the right side's functor embedding of the input profunctor
 * @return the covariantly reframed {@link Optic}
 */
static <P extends Profunctor<?,?,? extends P>,F extends Functor<?,? extends F>,S,T,A,B>Optic<P,F,S,T,A,B> reframe(Optic<? super P,? super F,S,T,A,B> optic){
  return Optic.optic(optic.<P,F,Functor<B,? extends F>,Functor<T,? extends F>,Profunctor<A,Functor<B,? extends F>,? extends P>,Profunctor<S,Functor<T,? extends F>,? extends P>>monomorphize());
}
