@Override public SignatureVisitor visitSuperclass(){
  endFormals();
  separator=EXTENDS_SEPARATOR;
  startType();
  return this;
}
