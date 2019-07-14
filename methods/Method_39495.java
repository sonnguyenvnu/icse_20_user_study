@Override public SignatureVisitor visitReturnType(){
  endFormals();
  if (parameterTypeVisited) {
    parameterTypeVisited=false;
  }
 else {
    declaration.append('(');
  }
  declaration.append(')');
  returnType=new StringBuilder();
  return new TraceSignatureVisitor(returnType);
}
