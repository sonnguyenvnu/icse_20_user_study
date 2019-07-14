private static void writeElement(Writer writer,Element element) throws IOException {
  writer.write('<');
  writer.write(element.getTagName());
  if (element.hasAttributes()) {
    NamedNodeMap attributes=element.getAttributes();
    for (int i=0; i < attributes.getLength(); i++) {
      Attr attribute=(Attr)attributes.item(i);
      writer.write(" " + attribute.getName());
      writer.write("=\"" + attribute.getValue() + "\"");
    }
  }
  SMILElement childElement=(SMILElement)element.getFirstChild();
  if (childElement != null) {
    writer.write('>');
    do {
      writeElement(writer,childElement);
      childElement=(SMILElement)childElement.getNextSibling();
    }
 while (childElement != null);
    writer.write("</");
    writer.write(element.getTagName());
    writer.write('>');
  }
 else {
    writer.write("/>");
  }
}
