private void emitOutdent(){
  for (int i=0; i < elements.size() - 1; i++) {
    writer.print(indentSpaces);
  }
}
