public static <A>Fold<Either<A,A>,A> codiagonal(){
  return new Fold<Either<A,A>,A>(){
    @Override public <B>F<Either<A,A>,B> foldMap(    final Monoid<B> m,    final F<A,B> f){
      return e -> e.either(f,f);
    }
  }
;
}
