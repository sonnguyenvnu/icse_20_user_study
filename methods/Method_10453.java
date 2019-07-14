private double getPPIOfDevice(){
  Point point=new Point();
  Activity activity=(Activity)context;
  activity.getWindowManager().getDefaultDisplay().getRealSize(point);
  DisplayMetrics dm=getResources().getDisplayMetrics();
  double x=Math.pow(point.x / dm.xdpi,2);
  double y=Math.pow(point.y / dm.ydpi,2);
  double screenInches=Math.sqrt(x + y);
  Double ppi=Math.sqrt(Math.pow(point.x,2) + Math.pow(point.y,2)) / screenInches;
  return ppi;
}
