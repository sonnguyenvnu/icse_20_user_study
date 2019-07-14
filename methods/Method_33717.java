/** 
 * ???????
 */
public static int getDisplayWidth(){
  try {
    WindowManager wm=(WindowManager)CloudReaderApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
    return wm.getDefaultDisplay().getWidth();
  }
 catch (  Exception e) {
    return 1080;
  }
}
