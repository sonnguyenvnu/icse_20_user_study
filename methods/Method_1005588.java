public void require(int type,String namespace,String name) throws XmlPullParserException, IOException {
  if (type != getEventType() || (namespace != null && !namespace.equals(getNamespace())) || (name != null && !name.equals(getName()))) {
    throw new XmlPullParserException(TYPES[type] + " is expected.",this,null);
  }
}
