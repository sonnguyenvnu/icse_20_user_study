public static <A,B,C,D,E,F$>P6<A,B,C,D,E,F$> lazy(F<Unit,A> fa,F<Unit,B> fb,F<Unit,C> fc,F<Unit,D> fd,F<Unit,E> fe,F<Unit,F$> ff){
  return new P6<A,B,C,D,E,F$>(){
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
    @Override public E _5(){
      return fe.f(unit());
    }
    @Override public F$ _6(){
      return ff.f(unit());
    }
  }
;
}
