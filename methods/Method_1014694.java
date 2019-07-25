/** 
 * take a screenshot
 * @param activity
 * @param filePath
 * @return
 */
public static boolean screenshot(Activity activity,String filePath){
  View decorView=activity.getWindow().getDecorView();
  decorView.setDrawingCacheEnabled(true);
  decorView.buildDrawingCache();
  Bitmap bitmap=decorView.getDrawingCache();
  File imagePath=new File(filePath);
  FileOutputStream fos=null;
  try {
    fos=new FileOutputStream(imagePath);
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
    fos.flush();
    return true;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    try {
      fos.close();
      if (null != bitmap) {
        bitmap.recycle();
        bitmap=null;
      }
    }
 catch (    Exception e) {
    }
    decorView.destroyDrawingCache();
    decorView.setDrawingCacheEnabled(false);
  }
  return false;
}
