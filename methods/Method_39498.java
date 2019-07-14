@Override public void visitInnerClassType(final String name){
  if (argumentStack % 2 != 0) {
    declaration.append('>');
  }
  argumentStack/=2;
  declaration.append('.');
  declaration.append(separator).append(name.replace('/','.'));
  separator="";
  argumentStack*=2;
}
