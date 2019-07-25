public synchronized void init() throws Exception {
  if (hierarchicalStreamDriver == null) {
    this.hierarchicalStreamDriver=new JettisonMappedXmlDriver();
  }
  if (reflectionProvider == null) {
    xstream=new XStream(hierarchicalStreamDriver);
  }
 else {
    xstream=new XStream(reflectionProvider,hierarchicalStreamDriver);
  }
}
