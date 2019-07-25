/** 
 * ???Snackbar?? ????:Snackbar.LENGTH_LONG
 * @param view
 * @param message
 * @return
 */
public static SnackbarUtils Long(View view,String message){
  return new SnackbarUtils(new WeakReference<>(Snackbar.make(view,message,Snackbar.LENGTH_LONG))).backColor(0XFF323232);
}
