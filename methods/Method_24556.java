public void beginDraw(){
  DOMImplementation domImpl=GenericDOMImplementation.getDOMImplementation();
  String ns="http://www.w3.org/2000/svg";
  Document document=domImpl.createDocument(ns,"svg",null);
  g2=new SVGGraphics2D(document);
  ((SVGGraphics2D)g2).setSVGCanvasSize(new Dimension(width,height));
  checkSettings();
  resetMatrix();
  vertexCount=0;
  pushMatrix();
}
