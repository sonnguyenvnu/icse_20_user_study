@Override public Iterator<Attribute> getXPathAttributesIterator(){
  return new AttributeAxisIterator(this);
}
