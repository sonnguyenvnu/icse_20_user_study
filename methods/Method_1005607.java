/** 
 * {@inheritDoc} 
 */
@Override public void annotate(ExceptionWithContext ex){
  for (int i=0; i < locals.length; i++) {
    TypeBearer type=locals[i];
    String s=(type == null) ? "<invalid>" : type.toString();
    ex.addContext("locals[" + Hex.u2(i) + "]: " + s);
  }
}
