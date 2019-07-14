/** 
 * ????Activity
 * @param intent
 * @param requestCode
 * @param showAnimation
 */
public static void toActivity(final Activity context,final Intent intent,final int requestCode,final boolean showAnimation){
  if (context == null || intent == null) {
    return;
  }
  context.runOnUiThread(new Runnable(){
    @Override public void run(){
      if (requestCode < 0) {
        context.startActivity(intent);
      }
 else {
        context.startActivityForResult(intent,requestCode);
      }
      if (showAnimation) {
        context.overridePendingTransition(R.anim.right_push_in,R.anim.hold);
      }
 else {
        context.overridePendingTransition(R.anim.null_anim,R.anim.null_anim);
      }
    }
  }
);
}
