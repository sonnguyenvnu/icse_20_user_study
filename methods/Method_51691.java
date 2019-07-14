private void init(){
  AttributeAxisIterator i=new AttributeAxisIterator(node);
  while (i.hasNext()) {
    Attribute attribute=i.next();
    add(new XPathFragmentAddingItem(attribute.getName() + " = " + attribute.getValue(),model,AttributeToolkit.constructPredicate(attribute)));
  }
}
