@Override public void setDynamicAttribute(final String uri,final String localName,final Object value){
  attrs.add(localName);
  attrs.add(value == null ? StringPool.EMPTY : value.toString());
}
