@Override public void directionUpdated(Direction direction){
  mView.showDirection(direction.getInitialBearing());
  mView.showDistance(direction.getDistance());
}
