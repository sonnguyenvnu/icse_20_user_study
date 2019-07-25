public Balloon rotate(RotationZoom rotationZoom){
  return new Balloon(rotationZoom.getPoint(center),rotationZoom.applyZoom(radius));
}
