public void decode(Object instance,Node base,Document document,List<String> filters) throws Exception {
  if (!isFilter(filters)) {
    return;
  }
  Node node=base;
  int len=path.segments.length;
  for (int i=0; i < len; i++) {
    Node n=DOMHelper.getElementNode(node,path.segments[i]);
    if (n == null) {
      Element element=document.createElement(path.segments[i]);
      node=node.appendChild(element);
    }
 else {
      node=n;
    }
  }
  Object object=getter.getValue(instance);
  if (object != null && Element.class.isAssignableFrom(object.getClass())) {
    return;
  }
  if (xao != null) {
    xao.decode(object,node,document,filters);
  }
 else {
    String value=object == null ? "" : object.toString();
    if (path.attribute != null && path.attribute.length() > 0) {
      Attr attr=document.createAttribute(path.attribute);
      attr.setNodeValue(value);
      ((Element)node).setAttributeNode(attr);
    }
 else {
      if (cdata) {
        CDATASection cdataSection=document.createCDATASection(value);
        node.appendChild(cdataSection);
      }
 else {
        node.setTextContent(value);
      }
    }
  }
}
