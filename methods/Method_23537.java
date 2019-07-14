protected void parseRect(){
  kind=RECT;
  family=PRIMITIVE;
  params=new float[]{getFloatWithUnit(element,"x",svgWidth),getFloatWithUnit(element,"y",svgHeight),getFloatWithUnit(element,"width",svgWidth),getFloatWithUnit(element,"height",svgHeight)};
}
