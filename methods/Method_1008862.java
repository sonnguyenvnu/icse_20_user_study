@Override public List<Object> apply(Object wrapped){
  Object o=XmlUtils.unwrap(wrapped);
  if (o instanceof SdtElement) {
    sdtList.add(((SdtElement)o));
  }
  return null;
}
