private static Object unwrap(Object o){
  if (o == null)   return null;
  if (o instanceof javax.xml.bind.JAXBElement) {
    return ((JAXBElement)o).getValue();
  }
 else {
    return o;
  }
}
