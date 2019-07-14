private static TransformerFactory createTransformerFactory(){
  try {
    TransformerFactory transformerFactory=(TransformerFactory)Class.forName("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl").newInstance();
    transformerFactory.setAttribute("indent-number",2);
    return transformerFactory;
  }
 catch (  Exception e) {
    return TransformerFactory.newInstance();
  }
}
