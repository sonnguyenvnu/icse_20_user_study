@SuppressWarnings("unchecked") public void decode(Object instance,Node base,Document document,List<String> filters) throws Exception {
  if (!isFilter(filters)) {
    return;
  }
  Collection col=null;
  if (Collection.class.isAssignableFrom(type)) {
    col=(Collection)getter.getValue(instance);
  }
 else {
    if (type.isArray()) {
      col=new ArrayList();
      Object obj=getter.getValue(instance);
      int length=Array.getLength(obj);
      for (int i=0; i < length; i++) {
        col.add(Array.get(obj,i));
      }
    }
 else {
      throw new Exception("@XNodeList " + base.getNodeName() + " 'type' only support Collection ande Array type");
    }
  }
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
  for (  Object object : col) {
    Element element=document.createElement(name);
    node=lastParentNode.appendChild(element);
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
}
