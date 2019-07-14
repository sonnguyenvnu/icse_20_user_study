private void endFormals(){
  if (formalTypeParameterVisited) {
    declaration.append('>');
    formalTypeParameterVisited=false;
  }
}
