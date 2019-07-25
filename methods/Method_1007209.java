/** 
 * Provides a memoising P8 that remembers its values.
 * @return A P8 that calls this P8 once for any given element and remembers the value for subsequent calls.
 */
public final P8<A,B,C,D,E,F,G,H> memo(){
  P8<A,B,C,D,E,F,G,H> self=this;
  return new P8<A,B,C,D,E,F,G,H>(){
    public A _1(){
      return a._1();
    }
    public B _2(){
      return b._1();
    }
    public C _3(){
      return c._1();
    }
    public D _4(){
      return d._1();
    }
    public E _5(){
      return e._1();
    }
    public F _6(){
      return f._1();
    }
    public G _7(){
      return g._1();
    }
    public H _8(){
      return h._1();
    }
  }
;
}
