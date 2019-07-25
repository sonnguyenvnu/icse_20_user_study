public final <Z>F2W<A,B,Z> map(F<C,Z> f){
  return lift(F2Functions.map(this,f));
}
