@Override public void visitFormalTypeParameter(final String name){
  declaration.append(formalTypeParameterVisited ? COMMA_SEPARATOR : "<").append(name);
  formalTypeParameterVisited=true;
  interfaceBoundVisited=false;
}
