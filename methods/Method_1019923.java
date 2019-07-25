/** 
 * {@inheritDoc}
 */
@Override public <H>Choice8<A,B,C,D,E,F,G,H> diverge(){
  return match(Choice8::a,Choice8::b,Choice8::c,Choice8::d,Choice8::e,Choice8::f,Choice8::g);
}
