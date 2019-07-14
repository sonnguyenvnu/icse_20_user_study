public void process(MotionEvent event){
  int action=event.getActionMasked();
  float x=event.getX();
  float y=renderView.getHeight() - event.getY();
  tempPoint[0]=x;
  tempPoint[1]=y;
  invertMatrix.mapPoints(tempPoint);
  Point location=new Point(tempPoint[0],tempPoint[1],1.0f);
switch (action) {
case MotionEvent.ACTION_DOWN:
case MotionEvent.ACTION_MOVE:
{
      if (!beganDrawing) {
        beganDrawing=true;
        hasMoved=false;
        isFirst=true;
        lastLocation=location;
        points[0]=location;
        pointsCount=1;
        clearBuffer=true;
      }
 else {
        float distance=location.getDistanceTo(lastLocation);
        if (distance < AndroidUtilities.dp(5.0f)) {
          return;
        }
        if (!hasMoved) {
          renderView.onBeganDrawing();
          hasMoved=true;
        }
        points[pointsCount]=location;
        pointsCount++;
        if (pointsCount == 3) {
          smoothenAndPaintPoints(false);
        }
        lastLocation=location;
      }
    }
  break;
case MotionEvent.ACTION_UP:
{
  if (!hasMoved) {
    if (renderView.shouldDraw()) {
      location.edge=true;
      paintPath(new Path(location));
    }
    reset();
  }
 else   if (pointsCount > 0) {
    smoothenAndPaintPoints(true);
  }
  pointsCount=0;
  renderView.getPainting().commitStroke(renderView.getCurrentColor());
  beganDrawing=false;
  renderView.onFinishedDrawing(hasMoved);
}
break;
}
}
