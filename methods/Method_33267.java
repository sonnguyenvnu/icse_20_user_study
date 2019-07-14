private Point2D getPointFromSL(int saturation,int lightness,double radius){
  double dy=map(saturation,0,255,-radius,radius);
  double angle=0.;
  double angle1=angle + 2 * Math.PI / 3.;
  double angle2=angle1 + 2 * Math.PI / 3.;
  double x1=radius * Math.sin(angle1);
  double y1=radius * Math.cos(angle1);
  double x2=radius * Math.sin(angle2);
  double y2=radius * Math.cos(angle2);
  double dx=0;
  double[] circle=circleFrom3Points(new Point2D(x1,y1),new Point2D(dx,dy),new Point2D(x2,y2));
  double xArc=circle[0];
  double yArc=circle[1];
  double arcR=circle[2];
  double Arco1=Math.atan2(x1 - xArc,y1 - yArc);
  double Arco2=Math.atan2(x2 - xArc,y2 - yArc);
  double ArcoFinal=map(lightness,0,255,Arco2,Arco1);
  double finalX=xArc + arcR * Math.sin(ArcoFinal);
  double finalY=yArc + arcR * Math.cos(ArcoFinal);
  if (dy < y1) {
    ArcoFinal=map(lightness,0,255,Arco1,Arco2 + 2 * Math.PI);
    finalX=-xArc - arcR * Math.sin(ArcoFinal);
    finalY=yArc + arcR * Math.cos(ArcoFinal);
  }
  return new Point2D(finalX,finalY);
}
