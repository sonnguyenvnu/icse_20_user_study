public static float getViewRawY(View view){
  int[] location=new int[2];
  location[0]=0;
  location[1]=(int)view.getY();
  ((View)view.getParent()).getLocationInWindow(location);
  return location[1];
}
