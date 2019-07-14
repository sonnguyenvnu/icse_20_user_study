public Document asXml(Object contribution,List<String> filters) throws Exception {
  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
  factory.setFeature(DDD,true);
  factory.setNamespaceAware(false);
  DocumentBuilder builder=factory.newDocumentBuilder();
  Document document=builder.newDocument();
  if (contribution == null) {
    return document;
  }
  XAnnotatedObject xao=objects.get(contribution.getClass());
  if (xao != null) {
    xao.decode(contribution,document,document,filters);
  }
  return document;
}
