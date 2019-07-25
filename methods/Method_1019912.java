/** 
 * {@inheritDoc}
 */
@Override public final <C>Choice3<A,B,C> diverge(){
  return match(Choice3::a,Choice3::b);
}
