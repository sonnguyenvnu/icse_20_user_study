public Point getScreenSize(){
  Display display=windowManager.getDefaultDisplay();
  Point result=new Point();
  display.getSize(result);
  return result;
}
