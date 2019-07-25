public static <A,B,C>P3<A,B,C> lazy(F<Unit,A> fa,F<Unit,B> fb,F<Unit,C> fc){
  return new P3<A,B,C>(){
    @Override public A _1(){
      return fa.f(unit());
    }
    @Override public B _2(){
      return fb.f(unit());
    }
    @Override public C _3(){
      return fc.f(unit());
    }
  }
;
}
