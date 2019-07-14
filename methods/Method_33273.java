private void init(final DoubleProperty rotationAngle,final double initControlX1,final double initControlY1){
  final Circle innerCircle=new Circle(centerX,centerY,radius,Color.TRANSPARENT);
  final Circle outerCircle=new Circle(centerX,centerY,radius * 2,Color.web("blue",0.5));
  createQuadraticCurve(rotationAngle,initControlX1,initControlY1);
  final CubicCurve innerCircleCurve=new CubicCurve();
  innerCircleCurve.startXProperty().bind(curves.get(0).startXProperty());
  innerCircleCurve.startYProperty().bind(curves.get(0).startYProperty());
  innerCircleCurve.endXProperty().bind(curves.get(1).startXProperty());
  innerCircleCurve.endYProperty().bind(curves.get(1).startYProperty());
  curves.get(0).startXProperty().addListener((o,oldVal,newVal) -> {
    final Point2D controlPoint=makeControlPoint(newVal.doubleValue(),curves.get(0).getStartY(),innerCircle,shapesNumber,-1);
    innerCircleCurve.setControlX1(controlPoint.getX());
    innerCircleCurve.setControlY1(controlPoint.getY());
  }
);
  curves.get(0).startYProperty().addListener((o,oldVal,newVal) -> {
    final Point2D controlPoint=makeControlPoint(curves.get(0).getStartX(),newVal.doubleValue(),innerCircle,shapesNumber,-1);
    innerCircleCurve.setControlX1(controlPoint.getX());
    innerCircleCurve.setControlY1(controlPoint.getY());
  }
);
  curves.get(1).startXProperty().addListener((o,oldVal,newVal) -> {
    final Point2D controlPoint=makeControlPoint(newVal.doubleValue(),curves.get(1).getStartY(),innerCircle,shapesNumber,1);
    innerCircleCurve.setControlX2(controlPoint.getX());
    innerCircleCurve.setControlY2(controlPoint.getY());
  }
);
  curves.get(1).startYProperty().addListener((o,oldVal,newVal) -> {
    final Point2D controlPoint=makeControlPoint(curves.get(1).getStartX(),newVal.doubleValue(),innerCircle,shapesNumber,1);
    innerCircleCurve.setControlX2(controlPoint.getX());
    innerCircleCurve.setControlY2(controlPoint.getY());
  }
);
  Point2D controlPoint=makeControlPoint(curves.get(0).getStartX(),curves.get(0).getStartY(),innerCircle,shapesNumber,-1);
  innerCircleCurve.setControlX1(controlPoint.getX());
  innerCircleCurve.setControlY1(controlPoint.getY());
  controlPoint=makeControlPoint(curves.get(1).getStartX(),curves.get(1).getStartY(),innerCircle,shapesNumber,1);
  innerCircleCurve.setControlX2(controlPoint.getX());
  innerCircleCurve.setControlY2(controlPoint.getY());
  final CubicCurve outerCircleCurve=new CubicCurve();
  outerCircleCurve.startXProperty().bind(curves.get(0).endXProperty());
  outerCircleCurve.startYProperty().bind(curves.get(0).endYProperty());
  outerCircleCurve.endXProperty().bind(curves.get(1).endXProperty());
  outerCircleCurve.endYProperty().bind(curves.get(1).endYProperty());
  controlPoint=makeControlPoint(curves.get(0).getEndX(),curves.get(0).getEndY(),outerCircle,shapesNumber,-1);
  outerCircleCurve.setControlX1(controlPoint.getX());
  outerCircleCurve.setControlY1(controlPoint.getY());
  controlPoint=makeControlPoint(curves.get(1).getEndX(),curves.get(1).getEndY(),outerCircle,shapesNumber,1);
  outerCircleCurve.setControlX2(controlPoint.getX());
  outerCircleCurve.setControlY2(controlPoint.getY());
  startPoint=new MoveTo();
  startPoint.xProperty().bind(curves.get(0).startXProperty());
  startPoint.yProperty().bind(curves.get(0).startYProperty());
  curve0To=new CubicCurveTo();
  curve0To.controlX1Property().bind(curves.get(0).controlX1Property());
  curve0To.controlY1Property().bind(curves.get(0).controlY1Property());
  curve0To.controlX2Property().bind(curves.get(0).controlX2Property());
  curve0To.controlY2Property().bind(curves.get(0).controlY2Property());
  curve0To.xProperty().bind(curves.get(0).endXProperty());
  curve0To.yProperty().bind(curves.get(0).endYProperty());
  outerCircleCurveTo=new CubicCurveTo();
  outerCircleCurveTo.controlX1Property().bind(outerCircleCurve.controlX1Property());
  outerCircleCurveTo.controlY1Property().bind(outerCircleCurve.controlY1Property());
  outerCircleCurveTo.controlX2Property().bind(outerCircleCurve.controlX2Property());
  outerCircleCurveTo.controlY2Property().bind(outerCircleCurve.controlY2Property());
  outerCircleCurveTo.xProperty().bind(outerCircleCurve.endXProperty());
  outerCircleCurveTo.yProperty().bind(outerCircleCurve.endYProperty());
  curve1To=new CubicCurveTo();
  curve1To.controlX1Property().bind(curves.get(1).controlX2Property());
  curve1To.controlY1Property().bind(curves.get(1).controlY2Property());
  curve1To.controlX2Property().bind(curves.get(1).controlX1Property());
  curve1To.controlY2Property().bind(curves.get(1).controlY1Property());
  curve1To.xProperty().bind(curves.get(1).startXProperty());
  curve1To.yProperty().bind(curves.get(1).startYProperty());
  innerCircleCurveTo=new CubicCurveTo();
  innerCircleCurveTo.controlX1Property().bind(innerCircleCurve.controlX2Property());
  innerCircleCurveTo.controlY1Property().bind(innerCircleCurve.controlY2Property());
  innerCircleCurveTo.controlX2Property().bind(innerCircleCurve.controlX1Property());
  innerCircleCurveTo.controlY2Property().bind(innerCircleCurve.controlY1Property());
  innerCircleCurveTo.xProperty().bind(innerCircleCurve.startXProperty());
  innerCircleCurveTo.yProperty().bind(innerCircleCurve.startYProperty());
}
