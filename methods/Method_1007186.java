public static <A,B,C,D>P4<A,B,C,D> lazy(final F0<A> pa,final F0<B> pb,final F0<C> pc,final F0<D> pd){
  return new P4<A,B,C,D>(){
    @Override public A _1(){
      return pa.f();
    }
    @Override public B _2(){
      return pb.f();
    }
    @Override public C _3(){
      return pc.f();
    }
    @Override public D _4(){
      return pd.f();
    }
  }
;
}
