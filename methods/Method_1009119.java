private static Object unwrap(Object o){
  if (o == null)   return null;
  if (o instanceof javax.xml.bind.JAXBElement) {
    log.debug("Unwrapped " + ((JAXBElement)o).getDeclaredType().getName());
    log.debug("name: " + ((JAXBElement)o).getName());
    return ((JAXBElement)o).getValue();
  }
 else {
    return o;
  }
}
