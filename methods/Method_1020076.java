/** 
 * Promote a  {@link ProtoOptic} with compatible bounds to an {@link Prism}.
 * @param protoOptic the {@link ProtoOptic}
 * @param < S >        the input that might fail to map to its output
 * @param < T >        the guaranteed output
 * @param < A >        the output that might fail to be produced
 * @param < B >        the input that guarantees its output
 * @return the {@link Prism}
 */
static <S,T,A,B>Prism<S,T,A,B> prism(ProtoOptic<? super Cocartesian<?,?,?>,S,T,A,B> protoOptic){
  return new Prism<S,T,A,B>(){
    @Override public <F extends Applicative<?,F>>Optic<Cocartesian<?,?,?>,F,S,T,A,B> toOptic(    Pure<F> pure){
      Optic<? super Cocartesian<?,?,?>,F,S,T,A,B> optic=protoOptic.toOptic(pure);
      return Optic.reframe(optic);
    }
  }
;
}
