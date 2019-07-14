/** 
 * Factory method for subclasses. 
 */
protected PShapeSVG createShape(PShapeSVG parent,XML properties,boolean parseKids){
  return new PShapeSVG(parent,properties,parseKids);
}
