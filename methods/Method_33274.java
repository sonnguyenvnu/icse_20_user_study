private void createQuadraticCurve(final DoubleProperty rotationAngle,final double initControlX1,final double initControlY1){
  for (int i=0; i < 2; i++) {
    double angle=2 * i * Math.PI / shapesNumber;
    double xOffset=radius * Math.cos(angle);
    double yOffset=radius * Math.sin(angle);
    final double startx=centerX + xOffset;
    final double starty=centerY + yOffset;
    final double diffStartCenterX=startx - centerX;
    final double diffStartCenterY=starty - centerY;
    final double sinRotAngle=Math.sin(rotationAngle.get());
    final double cosRotAngle=Math.cos(rotationAngle.get());
    final double startXR=cosRotAngle * diffStartCenterX - sinRotAngle * diffStartCenterY + centerX;
    final double startYR=sinRotAngle * diffStartCenterX + cosRotAngle * diffStartCenterY + centerY;
    angle=2 * i * Math.PI / shapesNumber;
    xOffset=distance * Math.cos(angle);
    yOffset=distance * Math.sin(angle);
    final double endx=centerX + xOffset;
    final double endy=centerY + yOffset;
    final CubicCurve curvedLine=new CubicCurve();
    curvedLine.setStartX(startXR);
    curvedLine.setStartY(startYR);
    curvedLine.setControlX1(startXR);
    curvedLine.setControlY1(startYR);
    curvedLine.setControlX2(endx);
    curvedLine.setControlY2(endy);
    curvedLine.setEndX(endx);
    curvedLine.setEndY(endy);
    curvedLine.setStroke(Color.FORESTGREEN);
    curvedLine.setStrokeWidth(1);
    curvedLine.setStrokeLineCap(StrokeLineCap.ROUND);
    curvedLine.setFill(Color.TRANSPARENT);
    curvedLine.setMouseTransparent(true);
    rotationAngle.addListener((o,oldVal,newVal) -> {
      final double newstartXR=((cosRotAngle * diffStartCenterX) - (sinRotAngle * diffStartCenterY)) + centerX;
      final double newstartYR=(sinRotAngle * diffStartCenterX) + (cosRotAngle * diffStartCenterY) + centerY;
      curvedLine.setStartX(newstartXR);
      curvedLine.setStartY(newstartYR);
    }
);
    curves.add(curvedLine);
    if (i == 0) {
      curvedLine.setControlX1(initControlX1);
      curvedLine.setControlY1(initControlY1);
    }
 else {
      final CubicCurve firstCurve=curves.get(0);
      final double curveTheta=2 * curves.indexOf(curvedLine) * Math.PI / shapesNumber;
      curvedLine.controlX1Property().bind(Bindings.createDoubleBinding(() -> {
        final double a=firstCurve.getControlX1() - centerX;
        final double b=Math.sin(curveTheta) * (firstCurve.getControlY1() - centerY);
        return ((Math.cos(curveTheta) * a) - b) + centerX;
      }
,firstCurve.controlX1Property(),firstCurve.controlY1Property()));
      curvedLine.controlY1Property().bind(Bindings.createDoubleBinding(() -> {
        final double a=Math.sin(curveTheta) * (firstCurve.getControlX1() - centerX);
        final double b=Math.cos(curveTheta) * (firstCurve.getControlY1() - centerY);
        return a + b + centerY;
      }
,firstCurve.controlX1Property(),firstCurve.controlY1Property()));
      curvedLine.controlX2Property().bind(Bindings.createDoubleBinding(() -> {
        final double a=firstCurve.getControlX2() - centerX;
        final double b=firstCurve.getControlY2() - centerY;
        return ((Math.cos(curveTheta) * a) - (Math.sin(curveTheta) * b)) + centerX;
      }
,firstCurve.controlX2Property(),firstCurve.controlY2Property()));
      curvedLine.controlY2Property().bind(Bindings.createDoubleBinding(() -> {
        final double a=Math.sin(curveTheta) * (firstCurve.getControlX2() - centerX);
        final double b=Math.cos(curveTheta) * (firstCurve.getControlY2() - centerY);
        return a + b + centerY;
      }
,firstCurve.controlX2Property(),firstCurve.controlY2Property()));
    }
  }
}
