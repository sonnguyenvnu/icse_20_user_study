public static void optimizeFactoriesLoading(){
  String transformerFactoryImpl=TransformerFactory.newInstance().getClass().getName();
  String xPathFactoryImpl=XPathFactory.newInstance().getClass().getName();
  setProperty(TransformerFactory.class.getName(),transformerFactoryImpl);
  setProperty(XPathFactory.DEFAULT_PROPERTY_NAME + ":" + XPathFactory.DEFAULT_OBJECT_MODEL_URI,xPathFactoryImpl);
  XMLUnit.setTransformerFactory(transformerFactoryImpl);
  XMLUnit.setXPathFactory(xPathFactoryImpl);
}
