@Override public void onRotationBegin(RotationGestureDetector rotationDetector){
  previousAngle=rotationDetector.getStartAngle();
  hasTransformed=true;
}
