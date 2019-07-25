private static void convert(SlidePart slide) throws XPathBinderAssociationIsPartialException, JAXBException {
  List<Object> results=slide.getJAXBNodesViaXPath("//p:spPr",false);
  for (  Object o : results) {
    CTShapeProperties spPr=(CTShapeProperties)o;
    if (spPr.getPrstGeom() == null) {
      System.out.println("- this shape not preset");
    }
 else {
      STShapeType shapeType=spPr.getPrstGeom().getPrst();
      CTCustomGeometry2D customGeo=PresetGeometries.getInstance().get(shapeType.value());
      if (customGeo == null) {
        System.out.println("- definition MISSING for " + shapeType.value());
      }
 else       if (shapeType.value().equals("leftArrow")) {
        System.out.println("- skipping " + shapeType.value() + " (corrupts pptx)");
      }
 else {
        spPr.setCustGeom(customGeo);
        spPr.setPrstGeom(null);
        System.out.println("- " + shapeType.value() + " processed");
      }
    }
  }
}
