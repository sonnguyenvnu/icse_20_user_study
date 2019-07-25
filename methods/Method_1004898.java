@Override public Object apply(final Element element){
  return null != element ? element.getProperty(name) : null;
}
