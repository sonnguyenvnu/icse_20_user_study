/** 
 * Provides a memoising P5 that remembers its values.
 * @return A P5 that calls this P5 once for any given element and remembers the value for subsequent calls.
 */
public final P5<A,B,C,D,E> memo(){
  P5<A,B,C,D,E> self=this;
  return new P5<A,B,C,D,E>(){
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
  }
;
}
