/** 
 * ???
 * @param textView ??
 * @param waitTime ??????
 * @param interval ????????
 * @param hint     ???????????
 */
public static void countDown(final TextView textView,long waitTime,long interval,final String hint){
  textView.setEnabled(false);
  android.os.CountDownTimer timer=new android.os.CountDownTimer(waitTime,interval){
    @SuppressLint("DefaultLocale") @Override public void onTick(    long millisUntilFinished){
      textView.setText(String.format("?? %d S",millisUntilFinished / 1000));
    }
    @Override public void onFinish(){
      textView.setEnabled(true);
      textView.setText(hint);
    }
  }
;
  timer.start();
}
