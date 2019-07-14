protected void parseImage(){
  kind=RECT;
  textureMode=NORMAL;
  family=PRIMITIVE;
  params=new float[]{getFloatWithUnit(element,"x",svgWidth),getFloatWithUnit(element,"y",svgHeight),getFloatWithUnit(element,"width",svgWidth),getFloatWithUnit(element,"height",svgHeight)};
  this.imagePath=element.getString("xlink:href");
}
