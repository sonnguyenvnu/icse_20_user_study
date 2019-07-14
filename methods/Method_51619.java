@Override public Source resolve(String href,String base) throws TransformerException {
  if (null == href || href.length() == 0) {
    return null;
  }
  try {
    String resource=href;
    ResourceLoader loader=new ResourceLoader();
    return new StreamSource(loader.getResourceStream(resource),resource);
  }
 catch (  Exception ex) {
    throw new TransformerException(ex);
  }
}
