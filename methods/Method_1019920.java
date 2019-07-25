/** 
 * {@inheritDoc}
 */
@Override public <F>Choice6<A,B,C,D,E,F> diverge(){
  return match(Choice6::a,Choice6::b,Choice6::c,Choice6::d,Choice6::e);
}
