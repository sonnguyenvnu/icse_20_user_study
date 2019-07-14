@Override public void mapChanged(double latitude,double longitude){
  Direction direction=mCodeActionsListener.codeLocationUpdated(latitude,longitude,false);
  if (direction.getToCode() != null) {
    mView.drawCodeArea(direction.getToCode());
  }
  mDirectionActionsListener.directionUpdated(direction);
}
