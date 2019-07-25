public final <X,Y>F2W<X,Y,C> contramap(F<X,A> f,F<Y,B> g){
  return lift(F2Functions.contramap(this,f,g));
}
