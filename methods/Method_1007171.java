public final <C>F1W<C,B> contramap(F<C,A> f){
  return lift(F1Functions.contramap(this,f));
}
