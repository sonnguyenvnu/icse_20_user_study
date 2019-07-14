/** 
 * Prepare the transformer, doing the proper "building"...
 * @param xslt The stylesheet provided as an InputStream
 */
private void prepareTransformer(InputStream xslt){
  if (xslt != null) {
    try {
      TransformerFactory factory=TransformerFactory.newInstance();
      StreamSource src=new StreamSource(xslt);
      this.transformer=factory.newTransformer(src);
    }
 catch (    TransformerConfigurationException e) {
      e.printStackTrace();
    }
  }
}
