/** 
 * ???Snackbar?? ????:Snackbar.LENGTH_SHORT
 * @param view
 * @param message
 * @return
 */
public static SnackbarUtils Short(View view,String message){
  return new SnackbarUtils(new WeakReference<>(Snackbar.make(view,message,Snackbar.LENGTH_SHORT))).backColor(0XFF323232);
}
