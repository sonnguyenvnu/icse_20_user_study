private void transform(Document doc){
  DOMSource source=new DOMSource(doc);
  this.setWriter(new StringWriter());
  StreamResult result=new StreamResult(this.outputWriter);
  try {
    transformer.transform(source,result);
  }
 catch (  TransformerException e) {
    e.printStackTrace();
  }
}
