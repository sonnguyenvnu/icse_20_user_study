static protected PShape loadShapeImpl(PGraphics pg,String filename,String extension){
  if (extension.equals("svg") || extension.equals("svgz")) {
    PShapeSVG svg=new PShapeSVG(pg.parent.loadXML(filename));
    return PShapeOpenGL.createShape((PGraphicsOpenGL)pg,svg);
  }
  return null;
}
