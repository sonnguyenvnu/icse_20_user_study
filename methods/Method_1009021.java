/** 
 * Method length
 * @param namespace
 * @param localname
 * @return the number of elements {namespace}:localname under this element
 */
public int length(String namespace,String localname){
  int number=0;
  Node sibling=getFirstChild();
  while (sibling != null) {
    if (localname.equals(sibling.getLocalName()) && namespace.equals(sibling.getNamespaceURI())) {
      number++;
    }
    sibling=sibling.getNextSibling();
  }
  return number;
}
