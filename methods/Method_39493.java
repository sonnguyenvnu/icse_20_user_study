@Override public SignatureVisitor visitInterfaceBound(){
  separator=interfaceBoundVisited ? COMMA_SEPARATOR : EXTENDS_SEPARATOR;
  interfaceBoundVisited=true;
  startType();
  return this;
}
