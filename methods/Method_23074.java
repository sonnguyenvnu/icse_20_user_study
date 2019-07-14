void writeStartElement(String element,String... args){
  emitIndent();
  writer.print("<" + element);
  for (int i=0; i < args.length; i+=2) {
    String attr=args[i];
    String value=args[i + 1];
    writer.print(" " + attr + "=\"" + value + "\"");
  }
  writer.println(">");
  elements.push(element);
}
