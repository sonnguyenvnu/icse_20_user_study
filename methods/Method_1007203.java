/** 
 * Provides a memoising P3 that remembers its values.
 * @return A P3 that calls this P3 once for any given element and remembers the value for subsequent calls.
 */
public final P3<A,B,C> memo(){
  P3<A,B,C> self=this;
  return new P3<A,B,C>(){
    public A _1(){
      return a._1();
    }
    public B _2(){
      return b._1();
    }
    public C _3(){
      return c._1();
    }
  }
;
}
