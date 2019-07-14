/** 
 * will load SVG icons from icomoon font file (e.g font.svg)
 * @param url of the svg font file
 * @throws IOException
 */
public static void loadGlyphsFont(URL url) throws IOException {
  try {
    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
    docBuilder.setEntityResolver((publicId,systemId) -> {
      return new InputSource(new StringReader(""));
    }
);
    File svgFontFile=new File(url.toURI());
    Document doc=docBuilder.parse(svgFontFile);
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
      glyphsMap.put(svgFontFile.getName() + "." + glyphId,glyphPane);
    }
  }
 catch (  ParserConfigurationException|SAXException|URISyntaxException e) {
    e.printStackTrace();
  }
}
