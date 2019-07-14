void writeStartElement(String element){
  emitIndent();
  writer.println("<" + element + ">");
  elements.push(element);
}
