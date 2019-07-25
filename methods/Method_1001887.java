public void arc(double x,double y,int angleStart,int angleEnd,double radius){
  final StringBuilder sb=new StringBuilder();
  sb.append("\\draw[");
  if (color != null) {
    sb.append("color=" + getColorName(color) + ",");
  }
  if (fillcolor != null) {
    sb.append("fill=" + getColorName(fillcolor) + ",");
  }
  sb.append("line width=" + thickness + "pt] " + couple(x,y) + " arc (" + angleStart + ":" + angleEnd + ":" + format(radius) + "pt);");
  addCommand(sb);
}
