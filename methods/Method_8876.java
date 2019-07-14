private void selectSegmentWithPoint(float pointx){
  if (activeSegment != CurvesSegmentNone) {
    return;
  }
  float segmentWidth=actualArea.width / 5.0f;
  pointx-=actualArea.x;
  activeSegment=(int)Math.floor((pointx / segmentWidth) + 1);
}
