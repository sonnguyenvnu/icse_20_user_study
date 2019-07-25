private void round(double r,double[] points){
  assert points.length % 2 == 0;
  final StringBuilder sb=new StringBuilder();
  appendShadeOrDraw(sb);
  sb.append("line width=" + thickness + "pt]");
  sb.append(" ");
  int i=0;
  sb.append(couple(points[i++],points[i++]));
  sb.append(" arc (180:270:" + format(r) + "pt) -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" arc (270:360:" + format(r) + "pt) -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" arc (0:90:" + format(r) + "pt) -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" arc (90:180:" + format(r) + "pt) -- ");
  sb.append(couple(points[i++],points[i++]));
  sb.append(" -- ");
  sb.append("cycle;");
  addCommand(sb);
}
