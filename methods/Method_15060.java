/** 
 * ????Activity
 * @param intent
 * @param requestCode
 * @param showAnimation
 */
public void toActivity(final Intent intent,final int requestCode,final boolean showAnimation){
  runUiThread(new Runnable(){
    @Override public void run(){
      if (intent == null) {
        Log.w(TAG,"toActivity  intent == null >> return;");
        return;
      }
      if (requestCode < 0) {
        startActivity(intent);
      }
 else {
        startActivityForResult(intent,requestCode);
      }
      if (showAnimation) {
        overridePendingTransition(R.anim.right_push_in,R.anim.hold);
      }
 else {
        overridePendingTransition(R.anim.null_anim,R.anim.null_anim);
      }
    }
  }
);
}
