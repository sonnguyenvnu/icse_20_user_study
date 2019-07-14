/** 
 * Parse a child XML element. Override this method to add parsing for more SVG elements.
 */
protected PShape parseChild(XML elem){
  String name=elem.getName();
  PShapeSVG shape=null;
  if (name == null) {
  }
 else   if (name.equals("g")) {
    shape=createShape(this,elem,true);
  }
 else   if (name.equals("defs")) {
    shape=createShape(this,elem,true);
  }
 else   if (name.equals("line")) {
    shape=createShape(this,elem,true);
    shape.parseLine();
  }
 else   if (name.equals("circle")) {
    shape=createShape(this,elem,true);
    shape.parseEllipse(true);
  }
 else   if (name.equals("ellipse")) {
    shape=createShape(this,elem,true);
    shape.parseEllipse(false);
  }
 else   if (name.equals("rect")) {
    shape=createShape(this,elem,true);
    shape.parseRect();
  }
 else   if (name.equals("image")) {
    shape=createShape(this,elem,true);
    shape.parseImage();
  }
 else   if (name.equals("polygon")) {
    shape=createShape(this,elem,true);
    shape.parsePoly(true);
  }
 else   if (name.equals("polyline")) {
    shape=createShape(this,elem,true);
    shape.parsePoly(false);
  }
 else   if (name.equals("path")) {
    shape=createShape(this,elem,true);
    shape.parsePath();
  }
 else   if (name.equals("radialGradient")) {
    return new RadialGradient(this,elem);
  }
 else   if (name.equals("linearGradient")) {
    return new LinearGradient(this,elem);
  }
 else   if (name.equals("font")) {
    return new Font(this,elem);
  }
 else   if (name.equals("text")) {
    return new Text(this,elem);
  }
 else   if (name.equals("tspan")) {
    return new LineOfText(this,elem);
  }
 else   if (name.equals("filter")) {
    PGraphics.showWarning("Filters are not supported.");
  }
 else   if (name.equals("mask")) {
    PGraphics.showWarning("Masks are not supported.");
  }
 else   if (name.equals("pattern")) {
    PGraphics.showWarning("Patterns are not supported.");
  }
 else   if (name.equals("stop")) {
  }
 else   if (name.equals("sodipodi:namedview")) {
  }
 else   if (name.equals("metadata") || name.equals("title") || name.equals("desc")) {
    return null;
  }
 else   if (!name.startsWith("#")) {
    PGraphics.showWarning("Ignoring <" + name + "> tag.");
  }
  return shape;
}
