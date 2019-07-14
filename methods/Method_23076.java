void writeEndElement(){
  emitOutdent();
  writer.println("</" + elements.pop() + ">");
}
