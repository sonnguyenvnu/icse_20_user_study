/** 
 * Provides a memoising P4 that remembers its values.
 * @return A P4 that calls this P4 once for any given element and remembers the value for subsequent calls.
 */
public final P4<A,B,C,D> memo(){
  P4<A,B,C,D> self=this;
  return new P4<A,B,C,D>(){
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
  }
;
}
