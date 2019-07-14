@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2) public static int getScreenHeight(Context context){
  WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
  Point p=new Point();
  wm.getDefaultDisplay().getSize(p);
  return p.y;
}
