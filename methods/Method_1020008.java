/** 
 * {@inheritDoc}
 */
@Override default Fn1<A,Tuple2<A,B>> carry(){
  return (Fn1<A,Tuple2<A,B>>)Cartesian.super.carry();
}
