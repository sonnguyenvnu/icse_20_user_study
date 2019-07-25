/** 
 * ???Snackbar?? ????:duration ??
 * @param view
 * @param message
 * @param duration ????(??)
 * @return
 */
public static SnackbarUtils Custom(View view,String message,int duration){
  return new SnackbarUtils(new WeakReference<>(Snackbar.make(view,message,Snackbar.LENGTH_SHORT).setDuration(duration))).backColor(0XFF323232);
}
