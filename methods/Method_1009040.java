/** 
 * If an object is wrapped in a JAXBElement, return the object. Warning: be careful with this. If you are copying objects into your document (rather than just reading them), you'll probably want the object to remain wrapped (JAXB usually wraps them for a reason; without the wrapper, you'll (probably?) need an 
 * @XmlRootElement annotation in order to be able to marshall ie save yourdocument).
 * @param o
 * @return
 */
public static Object unwrap(Object o){
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
