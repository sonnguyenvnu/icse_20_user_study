/** 
 * Parses site names and their configuration (e.g. "nyc" --> SiteConfig) into the map passed as argument 
 */
public static void parse(InputStream input,final Map<String,SiteConfig> map) throws Exception {
  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
  factory.setValidating(false);
  DocumentBuilder builder=factory.newDocumentBuilder();
  Document document=builder.parse(input);
  Element root=document.getDocumentElement();
  parse(root,map);
}
