@Override public SignatureVisitor visitClassBound(){
  separator=EXTENDS_SEPARATOR;
  startType();
  return this;
}
