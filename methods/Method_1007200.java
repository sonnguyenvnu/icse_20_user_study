/** 
 * Provides a memoising P2 that remembers its values.
 * @return A P2 that calls this P2 once for any given element and remembers the value for subsequent calls.
 */
public final P2<A,B> memo(){
  P2<A,B> self=this;
  return new P2<A,B>(){
    public A _1(){
      return a._1();
    }
    public B _2(){
      return b._1();
    }
  }
;
}
