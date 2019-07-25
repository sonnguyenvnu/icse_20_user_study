/** 
 * Redraw the pie chart.
 */
public void redraw(){
  if (!isAttached()) {
    return;
  }
  int width=canvas.getCoordinateSpaceWidth();
  int height=canvas.getCoordinateSpaceHeight();
  double radius=Math.min(width,height) / 2.0;
  double cx=width / 2.0;
  double cy=height / 2.0;
  Context2d context=canvas.getContext2d();
  context.clearRect(0,0,width,height);
  double totalWeight=0;
  for (  Slice slice : slices) {
    totalWeight+=slice.weight;
  }
  double startAngle=-0.5 * Math.PI;
  for (  Slice slice : slices) {
    double weight=slice.weight / totalWeight;
    double endAngle=startAngle + (weight * RADIANS_IN_CIRCLE);
    context.setFillStyle(slice.fill);
    context.beginPath();
    context.moveTo(cx,cy);
    context.arc(cx,cy,radius,startAngle,endAngle);
    context.fill();
    startAngle=endAngle;
  }
}
