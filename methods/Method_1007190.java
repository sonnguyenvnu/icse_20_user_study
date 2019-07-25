public static <A,B,C,D>P4<A,B,C,D> lazy(F<Unit,A> fa,F<Unit,B> fb,F<Unit,C> fc,F<Unit,D> fd){
  return new P4<A,B,C,D>(){
    @Override public A _1(){
      return fa.f(unit());
    }
    @Override public B _2(){
      return fb.f(unit());
    }
    @Override public C _3(){
      return fc.f(unit());
    }
    @Override public D _4(){
      return fd.f(unit());
    }
  }
;
}
