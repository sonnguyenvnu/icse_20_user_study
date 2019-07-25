public void quadto(double x1,double y1,double x2,double y2){
  currentPath.append("Q" + format(x1) + "," + format(y1) + " " + format(x2) + "," + format(y2) + " ");
  ensureVisible(x1,y1);
  ensureVisible(x2,y2);
}
