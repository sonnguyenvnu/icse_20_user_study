@Override public <B>B match(final F<One<V,A>,B> one,final F<Two<V,A>,B> two,final F<Three<V,A>,B> three,final F<Four<V,A>,B> four){
  return three.f(this);
}
