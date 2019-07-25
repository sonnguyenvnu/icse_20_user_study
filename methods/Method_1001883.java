public void polygon(double[] points){
  assert points.length % 2 == 0;
  final StringBuilder sb=new StringBuilder();
  appendShadeOrDraw(sb);
  sb.append("line width=" + thickness + "pt]");
  sb.append(" ");
  for (int i=0; i < points.length; i+=2) {
    sb.append(couple(points[i],points[i + 1]));
    sb.append(" -- ");
  }
  sb.append("cycle;");
  addCommand(sb);
}
