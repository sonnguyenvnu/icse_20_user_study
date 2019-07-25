public static <A,B,C,D,E>P5<A,B,C,D,E> lazy(final F0<A> pa,final F0<B> pb,final F0<C> pc,final F0<D> pd,F0<E> pe){
  return new P5<A,B,C,D,E>(){
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
    @Override public E _5(){
      return pe.f();
    }
  }
;
}
