@Override public List<Object> apply(Object o){
  if (o instanceof javax.xml.bind.JAXBElement && (((JAXBElement)o).getName().getLocalPart().equals("commentReference") || ((JAXBElement)o).getName().getLocalPart().equals("commentRangeStart") || ((JAXBElement)o).getName().getLocalPart().equals("commentRangeEnd"))) {
    commentElements.add((Child)XmlUtils.unwrap(o));
  }
 else   if (o instanceof CommentReference || o instanceof CommentRangeStart || o instanceof CommentRangeEnd) {
    commentElements.add((Child)o);
  }
  return null;
}
