public static Point getNavigationBarSize(Context context){
  Point appUsableSize=getAppUsableScreenSize(context);
  Point realScreenSize=getRealScreenSize(context);
  if (appUsableSize.x < realScreenSize.x) {
    return new Point(realScreenSize.x - appUsableSize.x,appUsableSize.y);
  }
  if (appUsableSize.y < realScreenSize.y) {
    return new Point(appUsableSize.x,realScreenSize.y - appUsableSize.y);
  }
  return new Point();
}
