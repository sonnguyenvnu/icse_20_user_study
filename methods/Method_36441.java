/** 
 * Processes the given DOM element and return the first mappable object found in the element. The given context is used.
 * @param ctx  the context to use
 * @param root the element to process
 * @return the first object found in this element or null if none
 * @throws Exception
 */
public Object load(Context ctx,Element root) throws Exception {
  String name=root.getNodeName();
  XAnnotatedObject xob=roots.get(name);
  if (xob != null) {
    return xob.newInstance(new Context(),root);
  }
 else {
    Node p=root.getFirstChild();
    while (p != null) {
      if (p.getNodeType() == Node.ELEMENT_NODE) {
        return load((Element)p);
      }
      p=p.getNextSibling();
    }
    return null;
  }
}
