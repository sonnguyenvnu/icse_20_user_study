/** 
 * ???Snackbar?? ????:Snackbar.LENGTH_INDEFINITE
 * @param view
 * @param message
 * @return
 */
public static SnackbarUtils Indefinite(View view,String message){
  return new SnackbarUtils(new WeakReference<>(Snackbar.make(view,message,Snackbar.LENGTH_INDEFINITE))).backColor(0XFF323232);
}
