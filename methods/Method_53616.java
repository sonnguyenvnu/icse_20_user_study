public void selectClosest(Point2D p){
  double min=Double.MAX_VALUE;
  int mini=-1;
  for (int i=0; i < values[POS].length; i++) {
    double dist=p.distance(values[POS][i],values[OFFSET][i]);
    if (min > dist) {
      mini=i;
      min=dist;
    }
  }
  if (mini != -1) {
    selected=mini;
    mKeyCycleNo.setText("" + selected);
    update();
  }
}
