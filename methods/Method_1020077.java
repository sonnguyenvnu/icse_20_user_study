/** 
 * Promote an  {@link Optic} with compatible bounds to an {@link Prism}. Note that because the  {@link Optic} mustguarantee an unbounded  {@link Functor} constraint in order to satisfy any future covariant constraint, theresulting  {@link Prism prism's} <code>toOptic</code> method will never need to consult its given{@link Pure lifting} function.
 * @param optic the {@link Optic}
 * @param < S >   the input that might fail to map to its output
 * @param < T >   the guaranteed output
 * @param < A >   the output that might fail to be produced
 * @param < B >   the input that guarantees its output
 * @return the {@link Prism}
 */
static <S,T,A,B>Prism<S,T,A,B> prism(Optic<? super Cocartesian<?,?,?>,? super Functor<?,?>,S,T,A,B> optic){
  return new Prism<S,T,A,B>(){
    @Override public <F extends Applicative<?,F>>Optic<Cocartesian<?,?,?>,F,S,T,A,B> toOptic(    Pure<F> pure){
      return Optic.reframe(optic);
    }
  }
;
}
