@SuppressWarnings("unchecked") @Override public void decode(Object instance,Node base,Document document,List<String> filters) throws Exception {
  if (!isFilter(filters)) {
    return;
  }
  Map values=(Map)getter.getValue(instance);
  Node node=base;
  int len=path.segments.length - 1;
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
  String name=path.segments[len];
  Node lastParentNode=node;
  Set<Map.Entry> entrys=values.entrySet();
  for (  Map.Entry entry : entrys) {
    Element element=document.createElement(name);
    node=lastParentNode.appendChild(element);
    Object keyObj=entry.getKey();
    String keyValue=keyObj == null ? "" : keyObj.toString();
    Object object=entry.getValue();
    Attr attrKey=document.createAttribute(key.attribute);
    attrKey.setNodeValue(keyValue);
    ((Element)node).setAttributeNode(attrKey);
    if (xao != null) {
      xao.decode(object,node,document,filters);
    }
 else {
      String value=object == null ? "" : object.toString();
      if (path.attribute != null && path.attribute.length() > 0) {
        Attr attrValue=document.createAttribute(path.attribute);
        attrValue.setNodeValue(value);
        ((Element)node).setAttributeNode(attrValue);
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
}
