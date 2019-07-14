@Override public void visitTypeVariable(final String name){
  declaration.append(separator).append(name);
  separator="";
  endType();
}
