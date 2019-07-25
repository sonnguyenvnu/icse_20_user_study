/** 
 * ??
 * @param element
 * @return
 */
public static XmlElement clone(XmlElement element){
  XmlElement destEle=new XmlElement(element.getName());
  for (  Attribute attribute : element.getAttributes()) {
    destEle.addAttribute(XmlElementTools.clone(attribute));
  }
  for (  Element ele : element.getElements()) {
    if (ele instanceof XmlElement) {
      destEle.addElement(XmlElementTools.clone((XmlElement)ele));
    }
 else     if (ele instanceof TextElement) {
      destEle.addElement(XmlElementTools.clone((TextElement)ele));
    }
  }
  return destEle;
}
