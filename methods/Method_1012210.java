/** 
 * ???????
 * @param activity
 * @param requestCode ???
 * @param theme       ??
 */
public static void start(Activity activity,int requestCode,int theme){
  Intent intent=new Intent(activity,CustomCaptureActivity.class);
  intent.putExtra(KEY_CAPTURE_THEME,theme);
  activity.startActivityForResult(intent,requestCode);
}
