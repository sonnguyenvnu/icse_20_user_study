protected void debugProcessing(String TAG,String state,String message,final int color){
  final String debugMessage=String.format(Locale.US,"%s-processing: %s",state,message);
  Log.d(TAG,debugMessage);
  runOnUiThread(new Runnable(){
    @Override public void run(){
      addView(getColoredView(color,debugMessage));
    }
  }
);
}
