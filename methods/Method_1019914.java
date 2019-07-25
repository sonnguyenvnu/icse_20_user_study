/** 
 * {@inheritDoc}
 */
@Override public final <D>Choice4<A,B,C,D> diverge(){
  return match(Choice4::a,Choice4::b,Choice4::c);
}
