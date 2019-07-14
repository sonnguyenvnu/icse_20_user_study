protected final void debugThrowable(String TAG,Throwable t){
  if (t != null) {
    Log.e(TAG,"AsyncHttpClient returned error",t);
    addView(getColoredView(LIGHTRED,throwableToString(t)));
  }
}
