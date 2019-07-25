/** 
 * {@inheritDoc}
 */
@Override public <R2>EitherT<M,L,R2> pure(R2 r2){
  return eitherT(melr.pure(right(r2)));
}
