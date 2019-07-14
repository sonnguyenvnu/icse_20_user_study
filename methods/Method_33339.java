private void updateArcLayout(double radius,double arcSize){
  arc.setRadiusX(radius);
  arc.setRadiusY(radius);
  arc.setCenterX(arcSize / 2);
  arc.setCenterY(arcSize / 2);
  track.setRadiusX(radius);
  track.setRadiusY(radius);
  track.setCenterX(arcSize / 2);
  track.setCenterY(arcSize / 2);
  track.setStrokeWidth(arc.getStrokeWidth());
}
