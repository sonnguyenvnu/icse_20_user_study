/** 
 * join two  {@link PSetter} with the same target 
 */
public final <S1,T1>PSetter<Either<S,S1>,Either<T,T1>,A,B> sum(final PSetter<S1,T1,A,B> other){
  return pSetter(f -> e -> e.bimap(modify(f),other.modify(f)));
}
