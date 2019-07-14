protected void parseLine(){
  kind=LINE;
  family=PRIMITIVE;
  params=new float[]{getFloatWithUnit(element,"x1",svgWidth),getFloatWithUnit(element,"y1",svgHeight),getFloatWithUnit(element,"x2",svgWidth),getFloatWithUnit(element,"y2",svgHeight)};
}
