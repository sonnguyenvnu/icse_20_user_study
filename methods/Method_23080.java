private void emitIndent(){
  for (int i=0; i < elements.size(); i++) {
    writer.print(indentSpaces);
  }
}
