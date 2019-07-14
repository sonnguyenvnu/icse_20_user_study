/** 
 * Handles parsing ellipse and circle tags.
 * @param circle true if this is a circle and not an ellipse
 */
protected void parseEllipse(boolean circle){
  kind=ELLIPSE;
  family=PRIMITIVE;
  params=new float[4];
  params[0]=getFloatWithUnit(element,"cx",svgWidth);
  params[1]=getFloatWithUnit(element,"cy",svgHeight);
  float rx, ry;
  if (circle) {
    rx=ry=getFloatWithUnit(element,"r",svgSizeXY);
  }
 else {
    rx=getFloatWithUnit(element,"rx",svgWidth);
    ry=getFloatWithUnit(element,"ry",svgHeight);
  }
  params[0]-=rx;
  params[1]-=ry;
  params[2]=rx * 2;
  params[3]=ry * 2;
}
