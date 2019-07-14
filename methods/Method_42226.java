private static Point getRealScreenSize(Context context){
  WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
  Display display=windowManager.getDefaultDisplay();
  Point size=new Point();
  display.getRealSize(size);
  return size;
}
