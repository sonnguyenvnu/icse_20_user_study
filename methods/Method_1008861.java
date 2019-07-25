@Override public List<Object> apply(Object o){
  if (o.getClass().getName().equals(startElement)) {
    if (o instanceof CTBookmark) {
      CTBookmark bookmark=(CTBookmark)o;
      starts.add(bookmark);
    }
  }
  if (o.getClass().getName().equals(endElement)) {
    if (o instanceof CTMarkupRange) {
      CTMarkupRange bookmark=(CTMarkupRange)o;
      ends.add(bookmark);
    }
  }
  if (startElement.equals("org.docx4j.wml.CTBookmark") && o instanceof javax.xml.bind.JAXBElement && ((JAXBElement)o).getName().getLocalPart().equals("instrText")) {
    refs.add((Text)XmlUtils.unwrap(o));
  }
  return null;
}
