public void ellipse(double x,double y,double width,double height){
  final StringBuilder sb=new StringBuilder();
  sb.append("\\draw[");
  if (color != null) {
    sb.append("color=" + getColorName(color) + ",");
  }
  if (fillcolor != null) {
    sb.append("fill=" + getColorName(fillcolor) + ",");
  }
  sb.append("line width=" + thickness + "pt] " + couple(x,y) + " ellipse (" + format(width) + "pt and " + format(height) + "pt);");
  addCommand(sb);
}
