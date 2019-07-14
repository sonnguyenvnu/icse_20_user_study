/** 
 * will load SVG icons from input stream
 * @param stream    input stream of svg font file
 * @param keyPrefix will be used as a prefix when storing SVG icons in the map
 * @throws IOException
 */
public static void loadGlyphsFont(InputStream stream,String keyPrefix) throws IOException {
  try {
    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
    docBuilder.setEntityResolver((publicId,systemId) -> {
      return new InputSource(new StringReader(""));
    }
);
    Document doc=docBuilder.parse(stream);
    doc.getDocumentElement().normalize();
    NodeList glyphsList=doc.getElementsByTagName("glyph");
    for (int i=0; i < glyphsList.getLength(); i++) {
      Node glyph=glyphsList.item(i);
      Node glyphName=glyph.getAttributes().getNamedItem("glyph-name");
      if (glyphName == null) {
        continue;
      }
      String glyphId=glyphName.getNodeValue();
      SVGGlyphBuilder glyphPane=new SVGGlyphBuilder(i,glyphId,glyph.getAttributes().getNamedItem("d").getNodeValue());
      glyphsMap.put(keyPrefix + "." + glyphId,glyphPane);
    }
    stream.close();
  }
 catch (  ParserConfigurationException|SAXException e) {
    e.printStackTrace();
  }
}
